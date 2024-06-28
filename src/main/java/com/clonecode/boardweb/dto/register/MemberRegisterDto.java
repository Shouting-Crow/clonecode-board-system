package com.clonecode.boardweb.dto.register;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberRegisterDto {

    @NotEmpty(message = "아이디는 필수 입니다.")
    private String loginId;

    @NotEmpty(message = "비밀번호를 입력해 주세요.")
    private String password;

    @NotEmpty(message = "본인의 이름은 필수 입니다.")
    private String name;

    @NotEmpty(message = "사용할 닉네임을 입력해 주세요.")
    private String nickname;

    @NotEmpty(message = "전화번호를 입력해 주세요.")
    private String phoneNumber;

    @NotEmpty(message = "현재 살고있는 도시를 입력해 주세요.")
    private String city;

    @NotEmpty(message = "도로명 주소를 입력해 주세요.")
    private String streetAddress;

}
