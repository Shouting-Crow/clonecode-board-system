package com.clonecode.boardweb.dto.board;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardUpdateDto {

    private Long id;
    private String title;
    private String content;

}
