package com.adela.dto;

import com.adela.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
    private Long categoryId;
    private Long userId;
    private String title;
    private String content;
    private String codeContent;
    private String errorContent;
    private LocalDate regDate;

    public Article toEntity(){
        return Article.builder()
                .categoryId(categoryId)
                .userId(userId)
                .title(title)
                .content(content)
                .codeContent(codeContent)
                .errorContent(errorContent)
                .regDate(regDate)
                .build();
    }
}
