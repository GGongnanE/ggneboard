package com.ggne.ggneboard.service;

import com.ggne.ggneboard.dto.CommentDTO;
import com.ggne.ggneboard.entity.Comment;
import com.ggne.ggneboard.entity.Post;
import com.ggne.ggneboard.entity.User;
import com.ggne.ggneboard.repository.CommentRepository;
import com.ggne.ggneboard.repository.PostRepository;
import com.ggne.ggneboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 댓글 생성
    @Transactional
    public CommentDTO createComment(Long postId, Long userId, String content) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));

        Comment comment = Comment.builder()
                .post(post)
                .user(user)
                .content(content)
                .build();

        commentRepository.save(comment);

        return new CommentDTO(comment);
    }

    // 특정 게시글의 댓글 조회
    public List<CommentDTO> getCommentsByPost(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(CommentDTO::new).collect(Collectors.toList());
    }

    // 댓글 수정
    @Transactional
    public CommentDTO updateComment(Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        comment.updateContent(content);
        return new CommentDTO(comment);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        comment.deleteComment();
        commentRepository.delete(comment);
    }
}
