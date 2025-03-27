package com.adela.repository;

import com.adela.domain.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<BoardComment, Long> {
    //단방향 관계이므로 BoardComment의 외래키인 boardid를 통해 댓글 조회하는 메소드
    List<BoardComment> findByArticle_BoardId(Long boardId);
}
