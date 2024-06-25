package com.clonecode.boardweb.service;

import com.clonecode.boardweb.domain.Member;
import com.clonecode.boardweb.dto.login.MemberRegisterDto;

public interface MemberRegisterService {
    Member registerMember(MemberRegisterDto dto);
}
