package com.clonecode.boardweb.dto.board;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter @Getter
public class BoardListDto {

    private Long id;

    private String memberName;

    private Long replyCount;

    private String title;

    private LocalDateTime createdDate;

    private Long viewCount;
}
