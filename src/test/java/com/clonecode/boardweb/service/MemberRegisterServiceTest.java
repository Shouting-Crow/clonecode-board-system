package com.clonecode.boardweb.service;

import com.clonecode.boardweb.domain.Member;
import com.clonecode.boardweb.dto.login.MemberRegisterDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRegisterServiceTest {

    @Autowired
    private MemberRegisterService memberRegisterService;

    @Test
    void memberRegister(){
        MemberRegisterDto dto = new MemberRegisterDto();
        dto.setLoginId("member1");
        dto.setPassword("1111");
        dto.setName("김국진");
        dto.setNickname("재미있는사람");
        dto.setPhoneNumber("01033446464");
        dto.setCity("서울");
        dto.setStreetAddress("강남대로 28길 11");

        Member member = memberRegisterService.registerMember(dto);

        assertThat(member.getId()).isEqualTo(1L);
        assertThat(member.getName()).isEqualTo("김국진");
        assertThat(member.getAddress().getCity()).isEqualTo("서울");
    }

}