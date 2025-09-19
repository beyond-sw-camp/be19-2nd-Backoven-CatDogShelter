package com.catdogshelter.repository.post;

import com.catdogshelter.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * 자유게시판 게시글(Post) 엔티티를 위한 Repository
 * - CRUD 기능 제공
 * - JPA를 통해 DB와 상호작용
 * - 게시글 조회, 추천, 신고 등 DB 조작 가능
 */


public interface PostRepository extends JpaRepository<Post, Integer> {
}
