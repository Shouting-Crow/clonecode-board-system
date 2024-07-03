package com.clonecode.boardweb.controller;

import com.clonecode.boardweb.domain.Member;
import com.clonecode.boardweb.domain.Reply;
import com.clonecode.boardweb.dto.reply.ReplyRegisterDto;
import com.clonecode.boardweb.dto.reply.ReplyUpdateDto;
import com.clonecode.boardweb.repository.ReplyRepository;
import com.clonecode.boardweb.service.reply.ReplyService;
import com.clonecode.boardweb.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/replies")
public class ReplyController {

    private final ReplyService replyService;
    private final ReplyRepository replyRepository;

    @PostMapping("/register")
    public String registerReply(@ModelAttribute(name = "dto")ReplyRegisterDto dto,
                                @SessionAttribute(name = SessionConst.LOGIN_MEMBER)Member member){
        if (member == null){
            return "redirect:/login";
        }

        dto.setMember(member);
        replyService.registerReply(dto);

        return "redirect:/board/" + dto.getBoardId();
    }

    @PostMapping("/delete")
    public String deleteReply(@RequestParam(name = "replyId") Long replyId){
        Long boardId = replyService.deleteReply(replyId);
        return "redirect:/board/" + boardId;
    }

    @GetMapping("/edit-form")
    public String viewEditForm(@RequestParam(name = "replyId") Long replyId,
                               Model model){
        Reply reply = replyRepository.findById(replyId).
                orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 입니다."));
        model.addAttribute("reply", reply);
        return "reply/reply-edit";
    }

    @PostMapping("/edit")
    public String editReply(@ModelAttribute(name = "dto") ReplyUpdateDto dto){
        replyService.updateReply(dto);
        return "redirect:/board/" + dto.getBoardId();
    }

}
