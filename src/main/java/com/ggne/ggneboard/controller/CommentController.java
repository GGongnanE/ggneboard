package com.ggne.ggneboard.controller;

import com.ggne.ggneboard.dto.CommentDTO;
import com.ggne.ggneboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 특정 게시글의 댓글 조회
    public ResponseEntity<List<CommentDTO>> getCommentsByPost(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }

    // 댓글 생성
    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestParam Long postId, @RequestParam Long userId, @RequestParam String content) {
        return ResponseEntity.ok(commentService.createComment(postId, userId, content));
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long commentId, @RequestParam String content) {
        return ResponseEntity.ok(commentService.updateComment(commentId, content));
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
