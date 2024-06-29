package com.clonecode.boardweb.service.board;

import com.clonecode.boardweb.dto.board.BoardListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    List<BoardListDto> getAllBoards();
    Page<BoardListDto> getAllBoardsByPaging(Pageable pageable);
}
