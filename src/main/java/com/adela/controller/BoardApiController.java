package com.adela.controller;

import com.adela.domain.Article;
import com.adela.domain.BoardComment;
import com.adela.dto.*;
import com.adela.service.BoardService;
import com.adela.service.CommentService;
import com.adela.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.adela.dto.ArticleResponse;
import com.adela.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardService boardService;
    private final CommentService commentService;
    private final UserService userService;

    @PostMapping("/board/article/{userId}")
    public ResponseEntity<Article> addArticle(@PathVariable String userId, @RequestBody AddArticleRequest request) {
        request.connectionUserEntity(userService.findById(userId));
        Article savedArticle = boardService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/board/list")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = boardService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();
        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/board/list/{articleId}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable("articleId") long id){
        Article article = boardService.findById(id);
        //count = 서비스 이용해서 게시글 좋아요 갯수 계산
        //viewArticle.set(count)
        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/board/article/{articleId}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("articleId") long id){
        boardService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/board/article/{articleId}")
    public ResponseEntity<Article> updateArticle(@PathVariable("articleId") long id, @RequestBody UpdateArticleRequest request){
        Article updateArticle = boardService.update(id, request);

        return ResponseEntity.ok()
                .body(updateArticle);
    }

    //댓글 기능
    //댓글 추가
    @PostMapping("/board/comment/{articleId}")
    public ResponseEntity<BoardComment> addComment(@PathVariable("articleId") long id, @RequestBody AddCommnetRequest request) {
        request.connectionArticle(boardService.findById(id));
        BoardComment savedComment = commentService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedComment);
    }

    //댓글 삭제
    @DeleteMapping("/board/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("commentId") long comment_id){
        commentService.delete(comment_id);
        return ResponseEntity.ok()
                .build();
    }

    //댓글 조회
    @GetMapping("/board/comment/list/{boardId}")
    public ResponseEntity<List<CommentResponse>> findByBoardIdComments(@PathVariable("boardId") long boardId) {
        List<CommentResponse> comments = commentService.findByBoardId(boardId)
                .stream()
                .map(CommentResponse::new)
                .toList();
        return ResponseEntity.ok().body(comments);
    }

    //댓글 수정
    @PutMapping("/board/comment/{commentId}")
    public ResponseEntity<String> updateArticle(@PathVariable("commentId") long id, @RequestBody UpdateCommentRequest request){
        BoardComment updateBoardComment = commentService.update(id, request);

        return ResponseEntity.ok("댓글이 수정되었습니다.");
    }
}
