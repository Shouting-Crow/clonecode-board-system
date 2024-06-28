package com.clonecode.boardweb.service.register;

import com.clonecode.boardweb.domain.Member;
import com.clonecode.boardweb.dto.register.MemberRegisterDto;

public interface MemberRegisterService {
    Member registerMember(MemberRegisterDto dto);
    boolean validateDuplicateMember(String loginId);
}
