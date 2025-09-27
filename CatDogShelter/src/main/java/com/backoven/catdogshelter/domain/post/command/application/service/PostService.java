package com.backoven.catdogshelter.domain.post.command.application.service;


import com.backoven.catdogshelter.common.util.DateTimeUtil;
import com.backoven.catdogshelter.domain.post.command.application.dto.*;
import com.backoven.catdogshelter.domain.post.command.domain.aggregate.entity.*;
import com.backoven.catdogshelter.domain.post.command.domain.repository.*;

import com.backoven.catdogshelter.domain.post.command.application.dto.*;
import com.backoven.catdogshelter.domain.post.command.domain.aggregate.entity.PostCommentEntity;
import com.backoven.catdogshelter.domain.post.command.domain.aggregate.entity.PostEntity;
import com.backoven.catdogshelter.domain.post.command.domain.aggregate.entity.PostFilesEntity;
import com.backoven.catdogshelter.domain.post.command.domain.repository.PostCommentRepository;
import com.backoven.catdogshelter.domain.post.command.domain.repository.PostFilesRepository;
import com.backoven.catdogshelter.domain.post.command.domain.repository.PostRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("postCommandService")
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final PostCommentRepository postCommentRepository;
    private final PostFilesRepository postFilesRepository;

    private final PostLikedRepository postLikedRepository;
    private final PostReportRepository postReportRepository;
    private final PostCommentReportRepository postCommentReportRepository;

    @Autowired
<<<<<<< HEAD
    public PostService(PostRepository postRepository, ModelMapper modelMapper, PostCommentRepository postCommentRepository, PostFilesRepository postFilesRepository, PostLikedRepository postLikedRepository, PostReportRepository postReportRepository) {


    @Autowired
    public PostService(PostRepository postRepository, ModelMapper modelMapper, PostCommentRepository postCommentRepository, PostFilesRepository postFilesRepository) {

=======
    public PostService(PostRepository postRepository, ModelMapper modelMapper,
                       PostCommentRepository postCommentRepository, PostFilesRepository postFilesRepository,
                       PostLikedRepository postLikedRepository,
                       PostReportRepository postReportRepository,
                       PostCommentReportRepository postCommentReportRepository) {
>>>>>>> 999573b (자유게시글 댓글 신고 조회 및 삽입 기능 작성)
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.postCommentRepository = postCommentRepository;
        this.postFilesRepository = postFilesRepository;
        this.postLikedRepository = postLikedRepository;

        this.postReportRepository = postReportRepository;
        this.postCommentReportRepository = postCommentReportRepository;
    }

    /* 자유게시글 삽입(insert) 부분 */
    @Transactional
    public void registPost(PostRegistDTO postregist) {
        PostEntity insertPost = modelMapper.map(postregist, PostEntity.class);
        postregist.setCreatedAt(DateTimeUtil.now());
        postRepository.save(insertPost);
        PostEntity entity = modelMapper.map(postregist, PostEntity.class);
        entity.setCreatedAtNow();
        postRepository.save(entity);

    }

    /* 자유게시글 수정(update) 부분 */
    @Transactional
    public void modifyPost(PostModifyDTO postModify) {
        PostEntity updatePost = postRepository.findById(postModify.getId())
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));
        postModify.setUpdatedAt(DateTimeUtil.now());
        // 수정할 값 덮어쓰기
        updatePost.setTitle(postModify.getTitle());
        updatePost.setContent(postModify.getContent());


        PostEntity foundPost = postRepository.findById(postModify.getId())
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

        // 수정할 값 덮어쓰기
        foundPost.setTitle(postModify.getTitle());
        foundPost.setContent(postModify.getContent());
        foundPost.setUpdatedAtNow();

        /* 둘 중 하나의 값이 먼저 들어오면 다른 값은 null로 처리되도록 함 */
        /* if 문을 안 썼을 경우, 둘의 값을 같이 넣어야 하고 하나만 넣었을 경우 둘 다 null 값으로 처리됨. */

    }

    /* 자유게시글 내용 논리적 삭제(delete) 부분 */
    @Transactional
    public void deletePost(int postDelete){

        PostEntity deletePost = postRepository.findById(postDelete)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

        deletePost.setIsDeleted(1);

        PostEntity foundPost = postRepository.findById(postDelete)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));
        foundPost.setIsDeleted(1);

    }

    /* 자유게시글 댓글 삽입(insert) 부분 */
    @Transactional
    public void registPostComment(PostCommentRegistDTO postCommentRegist){

        PostCommentEntity insertPostComment = modelMapper.map(postCommentRegist, PostCommentEntity.class);

        /* 파일이 삭제되어 있는 상태이면 댓글 삽입 불가 */
        if (insertPostComment.getIsDeleted() == 1) {
            throw new RuntimeException("삭제된 게시글에는 댓글을 달 수 없습니다.");
        }

        postCommentRegist.setCreatedAt(DateTimeUtil.now());
        postCommentRepository.save(insertPostComment);

        PostCommentEntity entity = modelMapper.map(postCommentRegist, PostCommentEntity.class);
        entity.setCreatedAtNow();
        postCommentRepository.save(entity);

    }

    /* 자유게시글 댓글 수정(update) 부분 */
    @Transactional
    public void modifyPostComment(PostCommentModifyDTO postCommentModify){

        PostCommentEntity updatePostComment = postCommentRepository.findById(postCommentModify.getId())
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

        /* 파일이 삭제되어 있는 상태이면 댓글 수정 불가 */
        if (updatePostComment.getIsDeleted() == 1) {
            throw new RuntimeException("삭제된 댓글은 수정할 수 없습니다.");
        }

        postCommentModify.setUpdatedAt(DateTimeUtil.now());
        updatePostComment.setContent(postCommentModify.getContent());

        PostCommentEntity foundComment = postCommentRepository.findById(postCommentModify.getId())
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

        foundComment.setContent(postCommentModify.getContent());
        foundComment.setUpdatedAtNow();

//        if (postCommentModify.getUserId() != null) {
//            foundComment.setUserId(postCommentModify.getUserId());
//            foundComment.setHeadId(null);
//        } else if (postCommentModify.getHeadId() != null) {
//            foundComment.setHeadId(postCommentModify.getHeadId());
//            foundComment.setUserId(null);
//        }

    }

    /* 자유게시글 댓글 삭제(delete) 부분 */
    @Transactional
    public void deletePostComment(int postCommentDelete){

        PostCommentEntity deletePostComment = postCommentRepository.findById(postCommentDelete)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

        /* 파일이 삭제되어 있는 상태이면 댓글 삭제 불가 */
        if (deletePostComment.getIsDeleted() == 1) {
            throw new RuntimeException("삭제된 댓글은 삭제할 수 없습니다.");
        }

        deletePostComment.setIsDeleted(1);

        PostCommentEntity foundComment = postCommentRepository.findById(postCommentDelete)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));
        foundComment.setIsDeleted(1);

    }

    /* 자유게시글 파일 삽입(insert) 부분 */
    @Transactional
    public void registPostFiles(PostFilesRegistDTO postFilesRegist){

        PostFilesEntity registPostFiles = modelMapper.map(postFilesRegist, PostFilesEntity.class);
        postFilesRegist.setUploadedAt(DateTimeUtil.now());
        postFilesRepository.save(registPostFiles);
    }

    /* 자유게시글 파일 수정(update) 부분 */

        PostFilesEntity entity = modelMapper.map(postFilesRegist, PostFilesEntity.class);
        entity.setUploadedAtNow();
        postFilesRepository.save(entity);
    }

    /* 자유게시글 파일 수정(modify) 부분 */

    @Transactional
    public void modifyPostFiles(PostFilesModifyDTO postFilesModify){
        PostFilesEntity foundFiles = postFilesRepository.findById(postFilesModify.getId())
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));


        postFilesModify.setUploadedAt(DateTimeUtil.now());
        foundFiles.setFileRename(postFilesModify.getFileRename());

        foundFiles.setFileRename(postFilesModify.getFileRename());
        foundFiles.setUploadedAtNow();

    }

    /* 자유게시글 파일 삭제(delete) 부분 */
    /* 자유게시글과 댓글은 is_deleted를 이용하여 논리적 삭제만 이루어졌지만, 파일의 경우 바로 삭제되므로 그에 맞게 코드 작성 */
    @Transactional
    public void deletePostFiles(PostFilesDeleteDTO postFilesDelete){
        int id = postFilesDelete.getId();
        postFilesRepository.deleteById(id);
    }


    /* 자유게시글 좋아요 수 증가 */
    /*  */
    @Transactional
    public void likedPost(PostLikedDTO postLiked){
        if (postLiked.getUserId() != null) {
            if (postLikedRepository.existsByPostIdAndUserId(postLiked.getPostId(), postLiked.getUserId())) {
                throw new RuntimeException("이미 좋아요를 눌렀습니다.");
            }
        }
        if (postLiked.getHeadId() != null) {
            if (postLikedRepository.existsByPostIdAndHeadId(postLiked.getPostId(), postLiked.getHeadId())) {
                throw new RuntimeException("이미 좋아요를 눌렀습니다.");
            }
        }

        postLikedRepository.save(modelMapper.map(postLiked, PostLikedEntity.class));
    }

    /* 자유게시글 신고 삽입 */
    @Transactional
    public void reportPost(PostReportDTO postReport){
        postReport.setCreatedAt(DateTimeUtil.now());

        if (postReport.getUserId() != null) {
            if (postReportRepository.existsByPostIdAndUserId(postReport.getPostId(), postReport.getUserId())) {
                throw new RuntimeException("이미 신고했던 게시글입니다.");
            }
        }
        if (postReport.getHeadId() != null) {
            if (postReportRepository.existsByPostIdAndHeadId(postReport.getPostId(), postReport.getHeadId())) {
                throw new RuntimeException("이미 신고했던 게시글입니다.");
            }
        }

        postReportRepository.save(modelMapper.map(postReport, PostReportEntity.class));
    }

<<<<<<< HEAD
=======
    /* 자유게시글 댓글 신고 삽입 */
    @Transactional
    public void reportPostComment(PostCommentReportDTO postCommentReport){
        postCommentReport.setCreatedAt(DateTimeUtil.now());

        if (postCommentReport.getUserId() != null) {
            if (postCommentReportRepository.existsByCommentIdAndUserId(postCommentReport.getCommentId(),
                                                             postCommentReport.getUserId())) {
                throw new RuntimeException("이미 신고했던 게시글 댓글입니다.");
            }
        }
        if (postCommentReport.getHeadId() != null) {
            if (postCommentReportRepository.existsByCommentIdAndHeadId(postCommentReport.getCommentId(),
                                                             postCommentReport.getHeadId())) {
                throw new RuntimeException("이미 신고했던 게시글 댓글입니다.");
            }
        }

        postCommentReportRepository.save(modelMapper.map(postCommentReport,  PostCommentReportEntity.class));
    }

>>>>>>> 999573b (자유게시글 댓글 신고 조회 및 삽입 기능 작성)
}
