package com.adela.service;

import com.adela.domain.Article;
import com.adela.dto.AddArticleRequest;
import com.adela.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public Article save(AddArticleRequest request){
        return boardRepository.save(request.toEntity());
        //생성된(toEntity) 객체를 인터페이스(repository)의 save를 통해 저장
    }

    public List<Article> findAll (){
        return boardRepository.findAll();
    }
}