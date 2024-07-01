package com.clonecode.boardweb.service.board;

import com.clonecode.boardweb.domain.Board;
import com.clonecode.boardweb.dto.board.BoardDetailDto;
import com.clonecode.boardweb.dto.board.BoardListDto;
import com.clonecode.boardweb.dto.board.BoardRegisterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    List<BoardListDto> getAllBoards();
    Page<BoardListDto> getAllBoardsByPaging(Pageable pageable);
    Board registerBoard(BoardRegisterDto boardRegisterDto);
    BoardDetailDto getBoardDetail(Long boardId);
}
