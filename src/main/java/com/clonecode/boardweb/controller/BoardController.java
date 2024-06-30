package com.clonecode.boardweb.controller;

import com.clonecode.boardweb.dto.board.BoardListDto;
import com.clonecode.boardweb.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards")
    public String viewBoardList(Model model, Pageable pageable){
        PageRequest pageRequest = PageRequest.of(
                pageable.getPageNumber(),
                10,
                Sort.by("id").descending()
        );

        Page<BoardListDto> boardList = boardService.getAllBoardsByPaging(pageRequest);
        List<BoardListDto> content = boardList.getContent();
        model.addAttribute("boardList", boardList);
        model.addAttribute("boards", content);

        return "board/board-list";
    }
}
