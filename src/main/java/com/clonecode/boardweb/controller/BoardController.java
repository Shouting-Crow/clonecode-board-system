package com.clonecode.boardweb.controller;

import com.clonecode.boardweb.domain.Member;
import com.clonecode.boardweb.dto.board.BoardListDto;
import com.clonecode.boardweb.dto.board.BoardRegisterDto;
import com.clonecode.boardweb.service.board.BoardService;
import com.clonecode.boardweb.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/board/register")
    public String viewRegisterBoard(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){
            return "redirect:/login?redirectURL=/board/register";
        }

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        BoardRegisterDto boardRegisterDto = new BoardRegisterDto();
        boardRegisterDto.setMember(loginMember);

        model.addAttribute("dto", boardRegisterDto);
        return "board/board-register";
    }

    @PostMapping("/board/register")
    public String registerBoard(@Valid @ModelAttribute(name = "dto") BoardRegisterDto dto,
                                BindingResult bindingResult,
                                HttpServletRequest request){
        if (bindingResult.hasErrors()){
            return "board/board-register";
        }

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){
            return "redirect:/login?redirectURL=/board/register";
        }

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        dto.setMember(loginMember);
        boardService.registerBoard(dto);

        return "redirect:/boards";
    }
}
