package com.ggne.ggneboard.service;

import com.ggne.ggneboard.entity.Post;
import com.ggne.ggneboard.entity.User;
import com.ggne.ggneboard.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceTest {

    // Mock : 가짜 객체를 만들어서 테스트를 진행할 때 사용
    @Mock
    private PostRepository postRepository;

    // @Mock으로 생성된 객체를 자동으로 주입하여 PostService 객체 생성
    @InjectMocks
    private PostService postService;

    // 샘플 데이터 객체 생성
    private Post samplePost;

    // 각 테스트 실행 전 공통 수행
    @BeforeEach
    void setUp() {
        // Mock 객체 초기화
        MockitoAnnotations.openMocks(this);
        User user = new User().builder().account("test123")
                .username("test")
                .password("password1234")
                .email("test@test.com").build();
        samplePost = new Post(user, "testTitle", "testContent", null);
    }

    @DisplayName("게시글 생성 테스트")
    @Test
    void createPostTest() {
        when(postRepository.save(samplePost)).thenReturn(samplePost);

        Post createdPost = postService.createPost(samplePost);
        assertNotNull(createdPost);
        assertEquals("testTitle", createdPost.getTitle());
    }

    @DisplayName("게시글 조회 테스트")
    @Test
    void testGetPost() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(samplePost));

        Optional<Post> foundPost = postService.getPost(1L);
        assertTrue(foundPost.isPresent());
        assertEquals("testTitle", foundPost.get().getTitle());
    }

    @DisplayName("게시글 전체 조회 테스트")
    @Test
    void testGetAllPosts() {
        when(postRepository.findAll()).thenReturn(List.of(samplePost));

        List<Post> posts = postService.getAllPosts();
        assertEquals(1, posts.size());
    }

    @DisplayName("게시글 업데이트 테스트")
    @Test
    void testUpdatePost() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(samplePost));

        Post updatedPost = postService.updatePost(1L, "Updated Title", "Updated Content");
        assertEquals("Updated Title", updatedPost.getTitle());
        assertEquals("Updated Content", updatedPost.getContent());
    }

    @DisplayName("게시글 삭제 테스트")
    @Test
    void testDeletePost() {
        doNothing().when(postRepository).deleteById(1L);

        postService.deletePost(1L);
        verify(postRepository, times(1)).deleteById(1L);
    }
}