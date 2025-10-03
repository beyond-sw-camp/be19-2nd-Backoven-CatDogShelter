package com.backoven.catdogshelter.domain.post.command.application.service;

import com.backoven.catdogshelter.domain.post.command.application.dto.*;
import com.backoven.catdogshelter.domain.post.command.domain.aggregate.entity.*;
import com.backoven.catdogshelter.domain.post.command.domain.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.backoven.catdogshelter.common.enumulation.ReportCategory.SPAM;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PostCommandServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostCommentRepository postCommentRepository;
    @Autowired
    private PostFilesRepository postFilesRepository;
    @Autowired
    private PostLikedRepository postLikedRepository;
    @Autowired
    private PostReportRepository postReportRepository;
    @Autowired
    private PostCommentReportRepository postCommentReportRepository;

    /* 자유게시글 테스트 */

    @Test
    void insertPost_thenFindById() {
        PostRegistDTO dto = new PostRegistDTO();
        dto.setTitle("테스트 제목");
        dto.setContent("테스트 내용");

        postService.registPost(dto);

        PostEntity saved = postRepository.findAll()
                .stream().reduce((f, s) -> s).orElseThrow();
        assertThat(saved.getTitle()).isEqualTo("테스트 제목");
        assertThat(saved.getContent()).isEqualTo("테스트 내용");
    }

    @Test
    void updatePost_thenCheckUpdatedFields() {
        PostEntity post = new PostEntity();
        post.setTitle("기존 제목");
        post.setContent("기존 내용");
        postRepository.save(post);

        PostModifyDTO modifyDTO = new PostModifyDTO();
        modifyDTO.setId(post.getId());
        modifyDTO.setTitle("수정된 제목");
        modifyDTO.setContent("수정된 내용");
        postService.modifyPost(modifyDTO);

        PostEntity updated = postRepository.findAll()
                .stream().reduce((f, s) -> s).orElseThrow();
        assertThat(updated.getTitle()).isEqualTo("수정된 제목");
    }

    @Test
    void deletePost_thenCheckIsDeletedFlag() {
        PostEntity post = new PostEntity();
        post.setTitle("삭제될 제목");
        post.setContent("삭제될 내용");
        postRepository.save(post);

        postService.deletePost(post.getId());

        PostEntity deleted = postRepository.findAll()
                .stream().reduce((f, s) -> s).orElseThrow();
        assertThat(deleted.getIsDeleted()).isEqualTo(1);
    }

    /* 자유게시글 댓글 테스트 */

    @Test
    void insertComment_thenFindById() {
        PostEntity post = new PostEntity();
        post.setTitle("게시글 제목");
        post.setContent("게시글 내용");
        postRepository.save(post);

        PostCommentRegistDTO dto = new PostCommentRegistDTO();
        dto.setPostId(String.valueOf(post.getId()));
        dto.setContent("댓글 내용");

        postService.registPostComment(dto);

        PostCommentEntity saved = postCommentRepository.findAll()
                .stream().reduce((f, s) -> s).orElseThrow();
        assertThat(saved.getContent()).isEqualTo("댓글 내용");
        assertThat(saved.getPostId()).isEqualTo(post.getId());
    }

    @Test
    void updateComment_thenCheckUpdatedFields() {
        PostEntity post = new PostEntity();
        post.setTitle("게시글 제목");
        post.setContent("게시글 내용");
        postRepository.save(post);

        PostCommentEntity comment = new PostCommentEntity();
        comment.setPostId(post.getId());
        comment.setContent("원본 댓글");
        postCommentRepository.save(comment);

        PostCommentModifyDTO dto = new PostCommentModifyDTO();
        dto.setId(comment.getId());
        dto.setContent("수정된 댓글");

        postService.modifyPostComment(dto);

        PostCommentEntity updated = postCommentRepository.findAll()
                .stream().reduce((f, s) -> s).orElseThrow();
        assertThat(updated.getContent()).isEqualTo("수정된 댓글");
    }

    @Test
    void deleteComment_thenCheckIsDeletedFlag() {
        PostEntity post = new PostEntity();
        post.setTitle("게시글 제목");
        post.setContent("게시글 내용");
        postRepository.save(post);

        PostCommentEntity comment = new PostCommentEntity();
        comment.setPostId(post.getId());
        comment.setContent("삭제될 댓글");
        postCommentRepository.save(comment);

        postService.deletePostComment(comment.getId());

        PostCommentEntity deleted = postCommentRepository.findAll()
                .stream().reduce((f, s) -> s).orElseThrow();
        assertThat(deleted.getIsDeleted()).isEqualTo(1);
    }

    /* 자유게시글 파일 테스트 */

    @Test
    void insertFile_thenFindById() {
        PostEntity post = new PostEntity();
        post.setTitle("게시글 제목");
        post.setContent("게시글 내용");
        postRepository.save(post);

        PostFilesRegistDTO dto = new PostFilesRegistDTO();
        dto.setPostId(post.getId());
        dto.setFileRename("file.png");
        dto.setFilePath("/uploads/file.png");

        postService.registPostFiles(dto);

        PostFilesEntity saved = postFilesRepository.findAll()
                .stream().reduce((f, s) -> s).orElseThrow();
        assertThat(saved.getFileRename()).isEqualTo("file.png");
    }

    @Test
    void updateFile_thenCheckUpdatedFields() {
        PostEntity post = new PostEntity();
        post.setTitle("게시글 제목");
        post.setContent("게시글 내용");
        postRepository.save(post);

        PostFilesEntity file = new PostFilesEntity();
        file.setPostId(post.getId());
        file.setFileRename("old.png");
        file.setFilePath("/uploads/old.png");
        postFilesRepository.save(file);

        PostFilesModifyDTO dto = new PostFilesModifyDTO();
        dto.setId(file.getId());
        dto.setFileRename("new.png");

        postService.modifyPostFiles(dto);

        PostFilesEntity updated = postFilesRepository.findAll()
                .stream().reduce((f, s) -> s).orElseThrow();
        assertThat(updated.getFileRename()).isEqualTo("new.png");
    }

    @Test
    void deleteFile_thenCheckRemoved() {
        PostEntity post = new PostEntity();
        post.setTitle("게시글 제목");
        post.setContent("게시글 내용");
        postRepository.save(post);

        PostFilesEntity file = new PostFilesEntity();
        file.setPostId(post.getId());
        file.setFileRename("del.png");
        file.setFilePath("/uploads/del.png");
        postFilesRepository.save(file);

        PostFilesDeleteDTO dto = new PostFilesDeleteDTO();
        dto.setId(file.getId());

        // when
        postService.deletePostFiles(dto);

        // then: id 기반으로 조회해서 없어졌는지 확인
        assertThat(postFilesRepository.findById(file.getId())).isEmpty();
    }


    /* 자유게시글 좋아요 테스트 */

    @Test
    void insertLike_thenCheckExists() {
        PostEntity post = new PostEntity();
        post.setTitle("게시글 제목");
        post.setContent("게시글 내용");
        postRepository.save(post);

        PostLikedDTO dto = new PostLikedDTO();
        dto.setPostId(post.getId());
        dto.setUserId(1);

        postService.likedPost(dto);

        PostLikedEntity saved = postLikedRepository.findAll()
                .stream().reduce((f, s) -> s).orElseThrow();
        assertThat(saved.getUserId()).isEqualTo(1);
        assertThat(saved.getPostId()).isEqualTo(post.getId());
    }

    /* 자유게시글/ 자유게시글 댓글 신고 테스트 */

    @Test
    void insertPostReport_thenCheckExists() {
        // given: 게시글 먼저 저장
        PostEntity post = new PostEntity();
        post.setTitle("제목");
        post.setContent("내용");
        postRepository.save(post);

        // when: 신고 등록
        PostReportDTO dto = new PostReportDTO();
        dto.setPostId(post.getId());
        dto.setUserId(1);
        dto.setHeadId(1);
        dto.setCategory(SPAM);
        dto.setEtcDetail("테스트 신고");

        postService.reportPost(dto);

        // then: 가장 최근 신고 가져와 확인
        PostReportEntity saved = postReportRepository.findAll()
                .stream().reduce((f, s) -> s).orElseThrow();

        assertThat(saved.getPostId()).isEqualTo(post.getId());
        assertThat(saved.getCategory()).isEqualTo(SPAM);
        assertThat(saved.getEtcDetail()).isEqualTo("테스트 신고");
    }


    @Test
    void insertCommentReport_thenCheckExists() {
        // given: 댓글 먼저 저장
        PostEntity post = new PostEntity();
        post.setTitle("제목");
        post.setContent("내용");
        postRepository.save(post);

        PostCommentEntity comment = new PostCommentEntity();
        comment.setContent("댓글 내용");
        comment.setPostId(post.getId());
        postCommentRepository.save(comment);

        // when: 신고 등록
        PostCommentReportDTO dto = new PostCommentReportDTO();
        dto.setCommentId(comment.getId());
        dto.setUserId(1);
        dto.setHeadId(1);
        dto.setCategory(SPAM);
        dto.setEtcDetail("테스트 신고");

        postService.reportPostComment(dto);

        // then: 가장 최근 신고 가져와 확인
        PostCommentReportEntity saved = postCommentReportRepository.findAll()
                .stream().reduce((f, s) -> s).orElseThrow();

        assertThat(saved.getCommentId()).isEqualTo(comment.getId());
        assertThat(saved.getCategory()).isEqualTo(SPAM);
        assertThat(saved.getEtcDetail()).isEqualTo("테스트 신고");
    }

}
