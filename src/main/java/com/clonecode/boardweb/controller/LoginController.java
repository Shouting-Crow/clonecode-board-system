package com.clonecode.boardweb.controller;

import com.clonecode.boardweb.domain.Member;
import com.clonecode.boardweb.dto.login.MemberLoginDto;
import com.clonecode.boardweb.service.login.MemberLoginService;
import com.clonecode.boardweb.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final MemberLoginService memberLoginService;

    @GetMapping("/login")
    public String viewLoginForm(Model model){
        model.addAttribute("loginDto", new MemberLoginDto());
        return "login/member-login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute(name = "loginDto") MemberLoginDto loginDto,
                        BindingResult bindingResult,
                        HttpServletRequest request){
        if (bindingResult.hasErrors()){
            return "login/member-login";
        }

        Optional<Member> member = memberLoginService.login(loginDto.getLoginId(), loginDto.getPassword());

        if (!member.isPresent()){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "login/member-login";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member.get());

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session != null){
            session.invalidate();
        }
        return "redirect:/";
    }

}
