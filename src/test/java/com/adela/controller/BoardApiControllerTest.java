package com.adela.controller;

import com.adela.domain.Article;
import com.adela.dto.UpdateArticleRequest;
import com.adela.repository.BoardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BoardApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BoardRepository boardRepository;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        boardRepository.deleteAll();
    }

    @DisplayName("findArticle: 게시글 상세 조회에 성공")
    @Test
    public void findArticle() throws Exception{
        final String url = "/board/list/{articleId}";
        final long boardId = 1;
        final long categoryId = 1;
        final long userId = 1;
        final String title = "test1";
        final String content = "this is content1";
        final String codeContent = "";
        final String errorContent = "";
        
        //테스트용 게시글 추가
        Article article = Article.builder()
                .categoryId(1L)
                .userId(1L)
                .title("test1")
                .content("this is content1")
                .regDate(LocalDate.now())
                .build();
        boardRepository.save(article);

        final ResultActions resultActions = mockMvc.perform(get(url, boardId));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value(categoryId))
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.content").value(content));
    }

    @DisplayName("updateArticle: 게시글 수정 성공")
    @Test
    public void updateArticle() throws Exception{
        final String url = "/board/article/{articleId}";

        //테스트용 게시글 추가
        Article article = Article.builder()
                .categoryId(1L)
                .userId(1L)
                .title("test1")
                .content("this is content1")
                .regDate(LocalDate.now())
                .build();
        boardRepository.save(article);

        final String title = "newtest";
        final String content = "this is new content";
        final String codeContent = "code!";
        final String errorContent = "error!";

        UpdateArticleRequest request = new UpdateArticleRequest(title, content, codeContent, errorContent);


        ResultActions result= mockMvc.perform(put(url, 1)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)));

        System.out.println(request.getTitle());

        result.andExpect(status().isOk());

        Article new_article = boardRepository.findById(1L).get();

        System.out.println(new_article.getTitle());

        assertThat(new_article.getTitle()).isEqualTo(title);
        assertThat(new_article.getContent()).isEqualTo(content);
        assertThat(new_article.getCodeContent()).isEqualTo(codeContent);
        assertThat(new_article.getErrorContent()).isEqualTo(errorContent);

    }

    @DisplayName("delete: 게시글 삭제 완료")
    @Test
    public void deleteArticle() throws Exception{
        final String url = "/board/article/{articleId}";
        final long boardId = 1;

        mockMvc.perform(delete(url, boardId))
                .andExpect(status().isOk());

        List<Article> articles = boardRepository.findAll();

        assertThat(articles).isEmpty();
    }

}