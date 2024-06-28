package com.clonecode.boardweb.service.login;

import com.clonecode.boardweb.domain.Member;

import java.util.Optional;

public interface MemberLoginService {
    Optional<Member> login(String loginId, String password);
}
