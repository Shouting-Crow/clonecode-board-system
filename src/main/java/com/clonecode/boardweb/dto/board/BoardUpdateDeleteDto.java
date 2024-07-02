package com.clonecode.boardweb.dto.board;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardUpdateDeleteDto {

    private Long id;
    private String title;
    private String content;

}
