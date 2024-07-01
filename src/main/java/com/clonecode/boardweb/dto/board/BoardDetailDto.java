package com.clonecode.boardweb.dto.board;

import com.clonecode.boardweb.domain.Member;
import com.clonecode.boardweb.dto.reply.ReplyDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter @Getter
public class BoardDetailDto {

    private Long id;
    private Member member;
    private String title;
    private String content;
    private Long viewCount;
    private LocalDateTime createdDate;
    private List<ReplyDto> replies;

}
