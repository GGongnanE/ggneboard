package com.ggne.ggneboard.service;

import com.ggne.ggneboard.entity.Post;
import com.ggne.ggneboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 게시글 생성
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    // 게시글 단건 조회
    public Optional<Post> getPost(Long postId) {
        return postRepository.findById(postId);
    }

    // 게시글 전체 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 게시글 제목 검색
    public List<Post> searchPostsByTitle(String title) {
        return postRepository.findByTitleContaining(title);
    }

    // 게시글 수정
    @Transactional
    public Post updatePost(Long postId, String newTitle, String newContent) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + postId));
        post.update(newTitle, newContent);
        return post;
    }

    // 게시글 삭제
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
