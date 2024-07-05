package com.clonecode.boardweb.dto.board;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class BoardSearchDto {

    private Long id;
    private String title;
    private String content;
    private String memberName;
}
