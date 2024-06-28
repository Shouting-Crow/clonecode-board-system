package com.clonecode.boardweb.controller;

import com.clonecode.boardweb.dto.register.MemberRegisterDto;
import com.clonecode.boardweb.service.register.MemberRegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberRegisterService memberRegisterService;

    @GetMapping("/register")
    public String viewRegisterForm(Model model){
        model.addAttribute("registerDto", new MemberRegisterDto());
        return "register/member";
    }

    @PostMapping("/register")
    public String registerMember(@Valid @ModelAttribute(name = "registerDto") MemberRegisterDto registerDto,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "register/member";
        }

        if (memberRegisterService.validateDuplicateMember(registerDto.getLoginId())){
            bindingResult.rejectValue("loginId", "duplicate", "이미 존재하는 계정입니다.");
            return "register/member";
        }

        memberRegisterService.registerMember(registerDto);
        return "redirect:/";
    }
}
