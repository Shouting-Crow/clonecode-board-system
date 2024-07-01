package com.clonecode.boardweb.dto.board;

import com.clonecode.boardweb.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class BoardRegisterDto {

    private Long id;

    private Member member;

    private String title;

    private String content;

    private LocalDateTime createdDate;
}
