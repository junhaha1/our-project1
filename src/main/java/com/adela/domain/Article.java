package com.adela.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardID", updatable = false)
    private Long boardId;

    @Column(name = "categoryID", nullable = false)
    private Long categoryId;

    @Column(name = "userID", nullable = false)
    private Long userId;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Content")
    private String content;

    @Column(name = "codeContent")
    private String codeContent;

    @Column(name = "errorContent")
    private String errorContent;

    @Column(name = "regDate", nullable = false)
    private LocalDate regDate;

    @Column(name = "updateDate")
    private LocalDate updateDate;

    @Builder
    public Article(Long categoryId, Long userId, String title, String content, String codeContent, String errorContent, LocalDate regDate){
        this.categoryId = categoryId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.codeContent = codeContent;
        this.errorContent = errorContent;
        this.regDate = regDate;
        this.updateDate = regDate;
    }

    @PrePersist
    protected void onCreate() {
        if (this.regDate == null) {
            this.regDate = LocalDate.now();
        }
        if (this.updateDate == null) {
            this.updateDate = LocalDate.now();
        }
    }
    public void update(String title, String content, String codeContent, String errorContent, LocalDate updateDate){
        this.title = title;
        this.content = content;
        this.codeContent = codeContent;
        this.errorContent = errorContent;
        this.updateDate = updateDate;

    }
}