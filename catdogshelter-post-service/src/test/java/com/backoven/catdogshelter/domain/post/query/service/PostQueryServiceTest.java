package com.backoven.catdogshelter.domain.post.query.service;

import com.backoven.catdogshelter.domain.post.query.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Sql(scripts = "/post-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class PostQueryServiceTest {

    @Autowired
    private PostService postService;

    /* 전체 자유게시글 조회*/
    @Test
    void selectPostInventory_shouldReturnList() {
        List<PostInventoryDTO> result = postService.selectPostInventory();
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getTitle()).isEqualTo("수정사항5");
    }

    /* 자유게시글 조회 */
    @Test
    void selectPostDetail_shouldReturnDetail() {
        PostDetailDTO detail = postService.selectPostDetail(1);
        assertThat(detail).isNotNull();
        assertThat(detail.getId()).isEqualTo(1);
        assertThat(detail.getTitle()).isEqualTo("수정사항5");
    }

    /* 자유게시글 추천 수 순 조회 */
    @Test
    void viewDescPostInventory_shouldReturnList() {
        List<PostInventoryDTO> result = postService.viewDescPostInventory();
        assertThat(result).isNotEmpty();

        // "테스트 게시글"이 제목에 포함된 게시글이 있는지 확인
        assertThat(result.stream().anyMatch(p ->
                p.getTitle() != null && p.getTitle().contains("테스트 게시글"))).isTrue();
    }

    /* 자유게시글 쵠신 수 순 조회 */
    @Test
    void createdAtPostInventory_shouldReturnList() {
        List<PostInventoryDTO> result = postService.createdAtPostInventory();
        assertThat(result).isNotEmpty();

        // created_at 값이 null이 아닌 게시글이 있는지 확인
        assertThat(result.stream().anyMatch(p -> p.getCreated_at() != null)).isTrue();
    }

    /* 자유게시글 추천 수 순 조회 */
    @Test
    void likedPostInventory_shouldReturnList() {
        List<PostLikedDescDTO> result = postService.likedPostInventory();
        assertThat(result).isNotEmpty();

        // 좋아요 수가 0 이상인 게시글이 있는지 확인
        assertThat(result.stream().anyMatch(p -> p.getLikeCount() >= 0)).isTrue();
    }

    /* 자유게시글 별 신고 내역 조회 */
    @Test
    void selectPostReport_shouldReturnList() {
        List<PostReportDetailDTO> result = postService.selectPostReport(1);
        assertThat(result).isNotEmpty();

        // post_id = 1 인 신고 내역이 포함되어 있는지 확인
        assertThat(result.stream().anyMatch(r -> r.getPost_id() == 1)).isTrue();
    }

    /* 자유게시글 댓글 별 신고 내역 조회 */
    @Test
    void selectPostCommentReport_shouldReturnList() {
        List<PostCommentReportDetailDTO> result = postService.selectPostCommentReport(1);
        assertThat(result).isNotEmpty();

        // comment_id = 1 인 신고 내역이 포함되어 있는지 확인
        assertThat(result.stream().anyMatch(r -> r.getComment_id() == 1)).isTrue();
    }
}
