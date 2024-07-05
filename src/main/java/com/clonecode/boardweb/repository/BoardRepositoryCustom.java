package com.clonecode.boardweb.repository;

import com.clonecode.boardweb.domain.Board;
import com.clonecode.boardweb.dto.board.BoardSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {
    Page<Board> search(BoardSearchDto dto, Pageable pageable);
}
