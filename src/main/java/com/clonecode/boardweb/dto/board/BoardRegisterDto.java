package com.clonecode.boardweb.dto.board;

import com.clonecode.boardweb.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class BoardRegisterDto {

    private Long id;

    private Member member;

    @NotBlank(message = "제목은 필수 항목입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 항목입니다.")
    private String content;

    private LocalDateTime createdDate;
}
