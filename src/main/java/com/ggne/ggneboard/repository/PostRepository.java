package com.ggne.ggneboard.repository;

import com.ggne.ggneboard.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // 제목 검색
    List<Post> findByTitleContaining(String title);
}
