package com.ggne.ggneboard.repository;

import com.ggne.ggneboard.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);    // 특정 게시글 ID로 댓글 조회
}
