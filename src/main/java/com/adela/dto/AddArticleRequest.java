package com.adela.dto;

import com.adela.domain.Article;
import com.adela.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
    private Long categoryId;
    private UserEntity userEntity;
    private String title;
    private String content;
    private String codeContent;
    private String errorContent;
    private LocalDate regDate;

    public void connectionUserEntity(UserEntity userEntity){
        this.userEntity = userEntity;
    }


    public Article toEntity(){
        return Article.builder()
                .categoryId(categoryId)
                .userEntity(userEntity)
                .title(title)
                .content(content)
                .codeContent(codeContent)
                .errorContent(errorContent)
                .regDate(regDate)
                .build();
    }
}