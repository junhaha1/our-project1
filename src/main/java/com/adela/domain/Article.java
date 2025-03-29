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
    @Column(name = "board_id", updatable = false)
    private Long boardId;

    @Column(name = "ct_id", nullable = false)
    private Long categoryId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "code_content")
    private String codeContent;

    @Column(name = "error_content")
    private String errorContent;

    @Column(name = "reg_date", nullable = false)
    private LocalDate regDate;

    @Column(name = "update_date")
    private LocalDate updateDate;

    @Builder
    public Article(Long categoryId, UserEntity userEntity, String title, String content, String codeContent, String errorContent, LocalDate regDate){
        this.categoryId = categoryId;
        this.userEntity = userEntity;
        this.title = title;
        this.content = content;
        this.codeContent = codeContent;
        this.errorContent = errorContent;
        this.regDate = regDate;
        this.updateDate = regDate;
    }

    @PrePersist
    protected void onCreate() {
        LocalDate date = LocalDate.now();
        if (this.regDate == null) {
            this.regDate = date;
        }
        if (this.updateDate == null) {
            this.updateDate = date;
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