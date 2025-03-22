package com.adela.dto;

import com.adela.domain.Article;

import java.time.LocalDate;

public class ArticleResponse {
    private final Long boardId;

    private final Long categoryId;

    private final Long userId;

    private final String title;

    private final String content;

    private final String codeContent;

    private final String errorContent;

    private final LocalDate regDate;

    private final LocalDate updateDate;

    public ArticleResponse(Article article){
        this.boardId = article.getBoardId();
        this.categoryId = article.getCategoryId();
        this.userId = article.getUserId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.codeContent = article.getCodeContent();
        this.errorContent = article.getErrorContent();
        this.regDate = article.getRegDate();
        this.updateDate = article.getUpdateDate();
    }
}
