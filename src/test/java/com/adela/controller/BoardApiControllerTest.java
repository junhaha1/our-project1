package com.adela.controller;

import com.adela.domain.Article;
import com.adela.dto.AddArticleRequest;
import com.adela.repository.BoardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BoardRepository boardRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        boardRepository.deleteAll();
    }

    @DisplayName("addArticle: 블로그 글 추가")
    @Test
    public void addArticle() throws Exception {
        // given
        final String url = "/board/article";
        final Long categoryId = 1L;
        final Long userId = 1001L;
        final String title = "title";
        final String content = "content";
        final String codeContent = "code";
        final String errorContent = "error";
        final AddArticleRequest userRequest = new AddArticleRequest(categoryId, userId, title, content, codeContent, errorContent, LocalDate.now());

        final String requestBody = objectMapper.writeValueAsString(userRequest);

        // when
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        // then
        result.andExpect(status().isCreated());

        List<Article> articles = boardRepository.findAll();

        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getCategoryId()).isEqualTo(categoryId);
        assertThat(articles.get(0).getUserId()).isEqualTo(userId);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
        assertThat(articles.get(0).getCodeContent()).isEqualTo(codeContent);
        assertThat(articles.get(0).getErrorContent()).isEqualTo(errorContent);
    }

    @DisplayName("findAllArticles: 블로그 글 목록 조회에 성공한다.")
    @Test
    public void findAllArticles() throws Exception {
        // given
        final String url = "/board/list";
        final Long categoryId = 1L;
        final Long userId = 1001L;
        final String title = "title";
        final String content = "content";
        final String codeContent = "code";
        final String errorContent = "error";

        boardRepository.save(Article.builder()
                .categoryId(categoryId)
                .userId(userId)
                .title(title)
                .content(content)
                .codeContent(codeContent)
                .errorContent(errorContent)
                .build());

        // when
        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].categoryId").value(categoryId))
                .andExpect(jsonPath("$[0].userId").value(userId))
                .andExpect(jsonPath("$[0].title").value(title))
                .andExpect(jsonPath("$[0].content").value(content))
                .andExpect(jsonPath("$[0].codeContent").value(codeContent))
                .andExpect(jsonPath("$[0].errorContent").value(errorContent));
    }
}