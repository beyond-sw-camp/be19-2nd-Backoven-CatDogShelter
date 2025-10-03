package com.backoven.catdogshelter.domain.post.query.service;

import com.backoven.catdogshelter.domain.post.query.dto.*;
import com.backoven.catdogshelter.domain.post.query.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/* service 명 중복 방지를 위해 alias 사용 */
@Service("postQueryService")
public class PostService {

    private final PostInventoryMapper postInventoryMapper;
    private final PostDetailMapper postDetailMapper;
    private final PostViewDescMapper postViewDescMapper;
    private final PostCreatedAtDescMapper postCreatedAtDescMapper;
    private final PostLikedDescMapper postLikedDescMapper;
    private final PostReportMapper postReportMapper;
    private final PostCommentReportDetailMapper postCommentReportDetailMapper;

    @Autowired
    public PostService(PostInventoryMapper postInventoryMapper, PostDetailMapper postDetailMapper,
                       PostViewDescMapper postViewDescMapper, PostCreatedAtDescMapper postCreatedAtDescMapper,
                       PostLikedDescMapper postLikedDescMapper, PostReportMapper postReportMapper, PostCommentReportDetailMapper postCommentReportDetailMapper
    ) {
        this.postInventoryMapper = postInventoryMapper;
        this.postDetailMapper = postDetailMapper;
        this.postViewDescMapper = postViewDescMapper;
        this.postCreatedAtDescMapper = postCreatedAtDescMapper;
        this.postLikedDescMapper = postLikedDescMapper;
        this.postReportMapper = postReportMapper;
        this.postCommentReportDetailMapper = postCommentReportDetailMapper;
    }

    public List<PostInventoryDTO> selectPostInventory() {
        return postInventoryMapper.selectPostInventory();
    }

    /* 지금은 DTO에서 readOnly로 select문 사용하기 때문에 Transactional가 굳이 필요하지 않지만 연습삼아 넘어봄 */
    /* 실무에서는 insert, update, delete 등 여러 sql문이 사용될 때 중간에 이상 현상이 발생하면 롤백하기 위해서 사용됨 */
    @Transactional(readOnly = true)
    public PostDetailDTO selectPostDetail(int id){
        return postDetailMapper.selectPostDetail(id);
    }

    /* 자유게시글 목록 조회에서 추천(view)에 따라 내림차순으로 정렬.
       이때 목록에서 정렬하므로 DTO는 목록 조회할 때 썼던거 그대로 사용 */
    public List<PostInventoryDTO> viewDescPostInventory() {
        return postViewDescMapper.selectPostViewDesc();
    }

    /* 자유게시글 목록 조회에서 생성일(created_at)에 따라 내림차순으로 정렬.
       이때 목록에서 정렬하므로 DTO는 목록 조회할 때 썼던거 그대로 사용 */
    public List<PostInventoryDTO> createdAtPostInventory() {
        return postCreatedAtDescMapper.selectPostCreatedAtDesc();
    }

    /* 자유게시글별 좋아요 수 조회 */
    public List<PostLikedDescDTO> likedPostInventory() {
        return postLikedDescMapper.selectPostLikedDesc();
    }

    /* 자유게시글별 신고 내역 상세 조회(신고 횟수 포함) */
    public List<PostReportDetailDTO> selectPostReport(int postId) {
        return postReportMapper.selectPostReportDetail(postId);
    }

    /* 자유게시글 댓급별 신고 내역 상세 조회(신고 횟수 포함) */
    public List<PostCommentReportDetailDTO> selectPostCommentReport(int postCommentId) {
        return postCommentReportDetailMapper.selectPostCommentReportDetail(postCommentId);
    }
}
