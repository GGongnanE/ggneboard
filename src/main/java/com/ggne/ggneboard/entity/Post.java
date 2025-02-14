package com.ggne.ggneboard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Table(name = "post")
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)     // 외래키(FK) 설정
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private int viewCount = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostTypeEnum postType;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Post와 Comment는 1:N 관계
    // mappedBy 속성은 양방향 매핑에서 연관관계의 주인을 설정
    // orphanRemoval 속성은 부모 엔티티와 연관관계가 끊어진 자식 엔티티를 자동으로 삭제
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    /**
     * Post 게시글 생성자
     * @param user
     * @param title
     * @param content
     * @param postType
     */
    public Post(User user, String title, String content, PostTypeEnum postType) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.postType = postType;
    }

    // 게시글 수정
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // JPA에서 엔티티 저장 시, 자동으로 현재 시간을 설정
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // 조회수 증가
    public synchronized void increaseViewCount() {
        this.viewCount++;
    }

    // 댓글 추가
    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setPost(this);
    }

    // 댓글 삭제
    public void removeComment(Comment comment) {
        this.comments.remove(comment);
        comment.setPost(null);
    }
}
