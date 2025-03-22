package com.adela.controller;

import com.adela.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

}
