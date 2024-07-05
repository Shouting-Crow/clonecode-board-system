package com.clonecode.boardweb.service.board;

import com.clonecode.boardweb.domain.Board;
import com.clonecode.boardweb.dto.board.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    Board registerBoard(BoardRegisterDto boardRegisterDto);
    BoardDetailDto getBoardDetail(Long boardId);
    void updateBoard(BoardUpdateDto dto);
    void deleteBoard(Long boardId);
    Page<BoardListDto> searchBoards(BoardSearchDto dto, Pageable pageable);
}
