package com.clonecode.boardweb.controller;

import com.clonecode.boardweb.domain.Member;
import com.clonecode.boardweb.dto.reply.ReplyRegisterDto;
import com.clonecode.boardweb.service.reply.ReplyService;
import com.clonecode.boardweb.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
@RequestMapping("/replies")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/register")
    public String registerReply(@ModelAttribute(name = "dto")ReplyRegisterDto dto,
                                @SessionAttribute(name = SessionConst.LOGIN_MEMBER)Member member){
        dto.setMember(member);
        replyService.registerReply(dto);

        return "redirect:/board/" + dto.getBoardId();
    }

}
