package com.adela.controller;

import com.adela.domain.Article;
import com.adela.dto.ArticleResponse;
import com.adela.dto.UpdateArticleRequest;
import com.adela.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping("/board/list/{articleId}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable("articleId") long id){
        Article article = boardService.findById(id);

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
}
