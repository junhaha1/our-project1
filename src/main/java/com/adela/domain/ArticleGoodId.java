package com.adela.domain;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class ArticleGoodId implements Serializable {
    private UserEntity userId;  // String userId → userEntity로 변경
    private Article boardId;  // Long boardId → Article로 변경
}
