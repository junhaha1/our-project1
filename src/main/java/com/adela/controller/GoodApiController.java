package com.adela.controller;

import com.adela.domain.Good;
import com.adela.dto.AddGoodRequest;
import com.adela.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class GoodApiController {
    private final GoodService goodService;

    @PostMapping("board/good")
    public ResponseEntity<Good> addGood(@RequestBody AddGoodRequest request){
        Good savedGood = goodService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedGood);
    }

    @DeleteMapping("/board/good/{userId}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("articleId") long id){
        goodService.delete(id);
        return ResponseEntity.ok()
                .build();
    }

}
