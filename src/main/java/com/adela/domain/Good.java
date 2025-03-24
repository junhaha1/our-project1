package com.adela.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Good {

    @Id
    @Column(name = "userID")
    private Long userId; //FK로 수정

    @Column(name = "Location", nullable = false)
    private Long location;

    @ManyToOne
    @JoinColumn(name = "boardID", referencedColumnName = "boardID")
    private Long boardId;

    @ManyToOne
    @JoinColumn(name = "commentID")
    private Long commentId; //FK로 수정

    @Builder
    public Good(Long userId, Long location, Long boardId, Long commentId) {
        this.userId = userId;
        this.location = location;
        this.boardId = boardId;
        this.commentId = commentId;
    }
}
