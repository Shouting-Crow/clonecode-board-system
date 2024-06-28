package com.clonecode.boardweb.service.login;

import com.clonecode.boardweb.domain.Member;
import com.clonecode.boardweb.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberLoginServiceImpl implements MemberLoginService{

    private final MemberRepository memberRepository;

    @Override
    public Optional<Member> login(String loginId, String password) {
        Optional<Member> member = memberRepository.findByLoginId(loginId);
        if (member.isPresent() && member.get().getPassword().equals(password)){
            return member;
        }

        return Optional.empty();
    }
}
