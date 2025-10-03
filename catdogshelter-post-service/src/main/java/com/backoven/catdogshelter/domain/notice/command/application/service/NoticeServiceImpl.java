// NoticeServiceImpl.java
package com.backoven.catdogshelter.domain.notice.command.application.service;

import com.backoven.catdogshelter.common.entity.ShelterheadEntity;
import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.common.repository.VolNoShelterHeadRepository;
import com.backoven.catdogshelter.common.repository.VolNoUserRepository;
import com.backoven.catdogshelter.common.util.DateTimeUtil;
import com.backoven.catdogshelter.domain.notice.command.application.dto.NoticeDTO;
import com.backoven.catdogshelter.domain.notice.command.application.dto.NoticeUpdateDTO;
import com.backoven.catdogshelter.domain.notice.command.domain.aggregate.entity.NoticeEntity;
import com.backoven.catdogshelter.domain.notice.command.domain.aggregate.entity.NoticeFileEntity;
import com.backoven.catdogshelter.domain.notice.command.domain.aggregate.entity.NoticeLikedEntity;
import com.backoven.catdogshelter.domain.notice.command.domain.repository.NoticeFileRepository;
import com.backoven.catdogshelter.domain.notice.command.domain.repository.NoticeLikedRepository;
import com.backoven.catdogshelter.domain.notice.command.domain.repository.NoticeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeFileRepository noticeFileRepository;
    private final NoticeLikedRepository noticeLikedRepository;
    private final VolNoUserRepository userRepository;
    private final VolNoShelterHeadRepository shelterHeadRepository;

    // --- 공지 작성 ---
    @Value("${file.upload-dir}")
    private String uploadDir; // 예: C:/uploadFiles/img/multi

    @Autowired
    public NoticeServiceImpl(NoticeRepository noticeRepository, NoticeFileRepository noticeFileRepository, NoticeLikedRepository noticeLikedRepository, VolNoUserRepository userRepository, VolNoShelterHeadRepository shelterHeadRepository) {
        this.noticeRepository = noticeRepository;
        this.noticeFileRepository = noticeFileRepository;
        this.noticeLikedRepository = noticeLikedRepository;
        this.userRepository = userRepository;
        this.shelterHeadRepository = shelterHeadRepository;
    }

    @Override
    @Transactional
    public long writeNotice(NoticeDTO dto, List<MultipartFile> files) {
        // 본문 저장
        NoticeEntity e = new NoticeEntity();
        e.setTitle(dto.getTitle());
        e.setContent(dto.getContent());
        e.setCreatedAt(DateTimeUtil.now()); // 서버 시간
        e.setRatingId(-1);
        noticeRepository.save(e);

        // 파일 저장
        if (files != null) {
            Path dir = Paths.get(uploadDir);
            try { Files.createDirectories(dir); } catch (IOException ex) { throw new RuntimeException(ex); }

            for (MultipartFile file : files) {
                if (file == null || file.isEmpty()) continue;

                String ext = Optional.ofNullable(file.getOriginalFilename())
                        .filter(n -> n.contains("."))
                        .map(n -> n.substring(n.lastIndexOf(".")))
                        .orElse("");
                String saved = UUID.randomUUID() + ext;
                Path savePath = dir.resolve(saved);

                try { file.transferTo(savePath.toFile()); }
                catch (IOException ex) { throw new RuntimeException("파일 저장 실패: " + file.getOriginalFilename(), ex); }

                NoticeFileEntity nf = new NoticeFileEntity();
                nf.setNotice(e);
                nf.setFileRename(saved);
                nf.setFilePath(savePath.toString());
                nf.setUploadedAt(DateTimeUtil.now());
                noticeFileRepository.save(nf);
            }
        }
        return e.getId();
    }

    // --- 공지 수정 ---
    @Override
    @Transactional
    public void modifyNotice(Long id, NoticeUpdateDTO dto, List<MultipartFile> newFiles, List<Integer> deleteFileIds) {
        NoticeEntity e = noticeRepository.findById(id.intValue())
                .orElseThrow(() -> new IllegalArgumentException("공지 없음: id=" + id));

        // 본문 변경
        e.setTitle(dto.getTitle());
        e.setContent(dto.getContent());
        e.setUpdatedAt(DateTimeUtil.now());     // 서버 시간

        // 1) 기존 파일 삭제 (선택)
        if (deleteFileIds != null && !deleteFileIds.isEmpty()) {
            List<NoticeFileEntity> toDelete = noticeFileRepository
                    .findAllByIdInAndNotice_Id(deleteFileIds, id);

            for (NoticeFileEntity nf : toDelete) {
                // DB 삭제
                noticeFileRepository.delete(nf);
                // 물리 파일도 제거 (파일 삭제는 트랜잭션 롤백과 무관하니 실패해도 서비스 중단 X)
                try {
                    Files.deleteIfExists(Paths.get(nf.getFilePath()));
                } catch (IOException ex) {
                    // 로그만 남기고 계속 진행 (필요하면 Logger로 경고)
                }
            }
        }

        // 2) 새 파일 추가 (선택)
        saveFiles(e, newFiles);
    }

    @Transactional
    protected void saveFiles(NoticeEntity e, List<MultipartFile> files) {
        if (files == null || files.isEmpty()) return;

        Path dir = Paths.get(uploadDir);
        try { Files.createDirectories(dir); } catch (IOException ex) { throw new RuntimeException(ex); }

        for (MultipartFile file : files) {
            if (file == null || file.isEmpty()) continue;

            String ext = Optional.ofNullable(file.getOriginalFilename())
                    .filter(n -> n.contains("."))
                    .map(n -> n.substring(n.lastIndexOf(".")))
                    .orElse("");
            String saved = UUID.randomUUID() + ext;
            Path savePath = dir.resolve(saved);

            try { file.transferTo(savePath.toFile()); }
            catch (IOException ex) { throw new RuntimeException("파일 저장 실패: " + file.getOriginalFilename(), ex); }

            NoticeFileEntity nf = new NoticeFileEntity();
            nf.setNotice(e);
            nf.setFileRename(saved);
            nf.setFilePath(savePath.toString());
            nf.setUploadedAt(DateTimeUtil.now());
            noticeFileRepository.save(nf);
        }
    }

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void toggleLike(Integer noticeId, Integer userId, Integer headId) {

        if ((userId == null && headId == null) || (userId != null && headId != null)) {
            throw new IllegalArgumentException("userId 또는 headId 중 하나만 보내야 합니다.");
        }

        var notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new IllegalArgumentException("공지 없음: id=" + noticeId));

        if (userId != null) {
            boolean exists = noticeLikedRepository.existsByNoticeIdAndUserId(noticeId, userId);
            if (exists) {
                noticeLikedRepository.deleteByNoticeIdAndUserId(noticeId, userId);
            } else {
                var like = new NoticeLikedEntity();
                like.setNotice(notice);
                like.setUser(em.getReference(UserEntity.class, userId));
                like.setHead(null);
                // 양방향이면 컬렉션에도 추가(NoticeEntity.likes)
                notice.getLikes().add(like);
            }
            return;
        }

        // headId != null
        boolean exists = noticeLikedRepository.existsByNoticeIdAndHeadId(noticeId, headId);
        if (exists) {
            noticeLikedRepository.deleteByNoticeIdAndHeadId(noticeId, headId);
        } else {
            var like = new NoticeLikedEntity();
            like.setNotice(notice);
            like.setUser(null);
            like.setHead(em.getReference(ShelterheadEntity.class, headId));
            notice.getLikes().add(like);
        }
    }
}
