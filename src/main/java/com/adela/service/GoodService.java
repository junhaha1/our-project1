package com.adela.service;

import com.adela.domain.Good;
import com.adela.dto.AddGoodRequest;
import com.adela.repository.GoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GoodService {
    private final GoodRepository goodRepository;

    public Good save (AddGoodRequest request){
        return goodRepository.save(request.toEntity());
    }

    public void delete(long id){
        goodRepository.deleteById(id);
    }
}
