package com.ggne.ggneboard.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table(name = "comment")
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)    // 외래키(FK) 설정
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)   // 외래키(FK) 설정
    private User user;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Comment 생성자
     * @param user
     * @param post
     * @param content
     */
    public Comment(User user, Post post, String content) {
        this.user = user;
        this.post = post;
        this.content = content;
        post.addComment(this);      // Post 엔티티에 Comment 추가
    }

    // 댓글 생성 시간 자동 설정
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // 댓글 수정
    public void updateContent(String content) {
        this.content = content;
    }

    // 댓글 삭제
    public void deleteComment() {
        post.getComments().remove(this);
    }

    // 댓글이 속한 게시글 설정 (단방향 매핑)
    protected void setPost(Post post) {
        this.post = post;
    }
}
