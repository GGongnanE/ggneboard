package com.ggne.ggneboard.service;


import com.ggne.ggneboard.entity.Post;
import com.ggne.ggneboard.entity.PostTypeEnum;
import com.ggne.ggneboard.entity.User;
import com.ggne.ggneboard.entity.UserRoleEnum;
import com.ggne.ggneboard.repository.PostRepository;
import com.ggne.ggneboard.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional                      // 테스트 메서드 실행 시 트랜잭션 시작하고, 테스트 완료 후 롤백
class PostServiceIntegrationTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void setUp() {
        // 테스트 유저 생성 및 저장
        testUser = userRepository.save(new User().builder()
                .account("test123")
                .username("test")
                .password("password1234")
                .role(UserRoleEnum.USER)
                .email("test9999@naver.com").build());
    }

    @DisplayName("게시글 생성 테스트")
    @Test
    void testCreatePost() {
        // given: 게시글 객체 생성
        Post post = new Post(testUser, "Integration Test Title",
                    "Integration Test Content", PostTypeEnum.FREE);

        // when: 게시글 저장
        Post savedPost = postService.createPost(post);

        // then: 저장된 게시글이 정상적으로 DB에 들어갔는지 확인
        assertNotNull(savedPost.getId());
        assertEquals("Integration Test Title", savedPost.getTitle());
        assertEquals("Integration Test Content", savedPost.getContent());

        // 실제 DB에서 조회
        Optional<Post> retrievedPost = postRepository.findById(savedPost.getId());
        assertTrue(retrievedPost.isPresent());
        assertEquals("Integration Test Title", retrievedPost.get().getTitle());
    }

    @DisplayName("게시글 조회 테스트")
    @Test
    void testGetPost() {
        // given: 게시글을 DB에 저장
        Post post = postRepository.save(
                new Post(testUser, "Test Title", "Test Content", PostTypeEnum.FREE));

        // when: 게시글 조회
        Optional<Post> foundPost = postService.getPost(post.getId());

        // then: 조회한 게시글이 존재하는지 확인
        assertTrue(foundPost.isPresent());
        assertEquals("Test Title", foundPost.get().getTitle());
    }

    @DisplayName("게시글 전체 조회 테스트")
    @Test
    void testGetAllPosts() {
        // given: 여러 개의 게시글 저장
        postRepository.save(new Post(testUser, "Title 1", "Content 1", PostTypeEnum.FREE));
        postRepository.save(new Post(testUser, "Title 2", "Content 2", PostTypeEnum.NOTICE));

        // when: 모든 게시글 조회
        List<Post> posts = postService.getAllPosts();

        // then: 2개의 게시글이 정상적으로 조회되는지 확인
        assertFalse(posts.isEmpty());
        assertEquals(2, posts.size());
    }

    @DisplayName("게시글 수정 테스트")
    @Test
    void testUpdatePost() {
        // given: 기존 게시글 저장
        Post post = postRepository.save(
                new Post(testUser, "Old Title", "Old Content", PostTypeEnum.QNA));

        // when: 게시글 수정
        Post updatedPost = postService.updatePost(post.getId(), "New Title", "New Content");

        // then: 수정된 내용 확인
        assertEquals("New Title", updatedPost.getTitle());
        assertEquals("New Content", updatedPost.getContent());
    }

    @DisplayName("게시글 삭제 테스트")
    @Test
    void testDeletePost() {
        // given: 삭제할 게시글 저장
        Post post = postRepository.save(
                new Post(testUser, "Delete Title", "Delete Content", PostTypeEnum.FREE));

        // when: 게시글 삭제
        postService.deletePost(post.getId());

        // then: 실제 DB에서 삭제되었는지 확인
        Optional<Post> deletedPost = postRepository.findById(post.getId());
        assertFalse(deletedPost.isPresent());
    }

}