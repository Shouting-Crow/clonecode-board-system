package com.clonecode.boardweb.dto.reply;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ReplyUpdateDto {

    private Long id;
    private String content;
    private LocalDateTime updatedDate;
    private Long boardId;

    public ReplyUpdateDto(Long id, String content) {
        this.id = id;
        this.content = content;
        this.updatedDate = LocalDateTime.now();
    }
}
