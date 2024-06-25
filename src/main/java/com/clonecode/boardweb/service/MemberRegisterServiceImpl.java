package com.clonecode.boardweb.service;

import com.clonecode.boardweb.domain.Address;
import com.clonecode.boardweb.domain.Member;
import com.clonecode.boardweb.dto.login.MemberRegisterDto;
import com.clonecode.boardweb.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberRegisterServiceImpl implements MemberRegisterService{

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Member registerMember(MemberRegisterDto dto) {
        validateDuplicateMember(dto.getLoginId());
        Member member = new Member();
        member.setName(dto.getName());
        member.setAddress(new Address(dto.getCity(), dto.getStreetAddress()));
        member.setLoginId(dto.getLoginId());
        member.setPassword(dto.getPassword());
        member.setNickname(dto.getNickname());
        member.setPhoneNumber(dto.getPhoneNumber());

        return memberRepository.save(member);
    }

    private void validateDuplicateMember(String loginId){
        if (memberRepository.findByLoginId(loginId).isPresent()){
            throw new IllegalStateException("이미 존재하는 계정입니다.");
        }
    }
}
