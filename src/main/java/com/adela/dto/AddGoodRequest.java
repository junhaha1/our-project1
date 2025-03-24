package com.adela.dto;

import com.adela.domain.Good;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddGoodRequest {
    private Long userId;
    private Long location;
    private Long boardId;
    private Long commentId;

    public Good toEntity(){
        return Good.builder()
                .userId(userId)
                .location(location)
                .boardId(boardId)
                .commentId(commentId)
                .build();
    }

}
