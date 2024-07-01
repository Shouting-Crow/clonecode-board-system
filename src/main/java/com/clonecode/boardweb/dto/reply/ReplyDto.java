package com.clonecode.boardweb.dto.reply;

import com.clonecode.boardweb.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ReplyDto {

    private Long id;
    private Member member;
    private String content;
    private LocalDateTime createdDate;

}
