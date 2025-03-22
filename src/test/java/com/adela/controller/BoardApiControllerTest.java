package com.adela.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class BoardApiControllerTest {
    @DisplayName("addArticle: 게시판 게시글 생성")
    @Test
    public void addArticle() throws Exception{
        //given
        final String url = "/board/article";
//        final Long categoryId = categoryId;
//        final Long userId = userId;
        final String title = "title";
        final String content = "content";
        final String codeContent = "codeContent";
        final String errorContent = "errorContent";
        final LocalDate regDate = LocalDate.parse("regDate");
    }
}