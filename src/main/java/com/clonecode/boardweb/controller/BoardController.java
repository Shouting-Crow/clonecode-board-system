package com.clonecode.boardweb.controller;

import com.clonecode.boardweb.domain.Member;
import com.clonecode.boardweb.dto.board.*;
import com.clonecode.boardweb.service.board.BoardService;
import com.clonecode.boardweb.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards")
    public String viewBoardList(Model model, BoardSearchDto dto, Pageable pageable){
        PageRequest pageRequest = PageRequest.of(
                pageable.getPageNumber(),
                10,
                Sort.by("id").descending()
        );

        Page<BoardListDto> boardList = boardService.searchBoards(dto, pageRequest);
        List<BoardListDto> content = boardList.getContent();
        model.addAttribute("boardList", boardList);
        model.addAttribute("boards", content);
        model.addAttribute("dto", dto);

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

    @GetMapping("/board/{id}")
    public String viewBoardDetail(@PathVariable(name = "id") Long id,
                                  @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,
                                  Model model){
        BoardDetailDto boardDetailDto = boardService.getBoardDetail(id);
        model.addAttribute("boardDetail", boardDetailDto);
        model.addAttribute("member", member);
        return "board/board-detail";
    }

    @GetMapping("/board/edit/{id}")
    public String viewBoardEdit(@PathVariable(name = "id") Long id,
                                Model model){
        BoardDetailDto boardDetail = boardService.getBoardDetail(id);
        model.addAttribute("boardDetail", boardDetail);
        return "board/board-edit";
    }

    @PostMapping("/board/update")
    public String updateBoard(@ModelAttribute(name = "dto") BoardUpdateDto dto){
        boardService.updateBoard(dto);
        return "redirect:/board/" + dto.getId();
    }

    @PostMapping("/board/delete")
    public String deleteBoard(@RequestParam(name = "id") Long id){
        boardService.deleteBoard(id);
        return "redirect:/boards";
    }
}
