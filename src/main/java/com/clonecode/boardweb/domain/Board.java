package com.clonecode.boardweb.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board")
    private List<Reply> reply = new ArrayList<>();

    private String title;

    private String content;

    @CreatedDate
    private LocalDateTime createdDate;

    private Long viewCount = 0L;
    private Long replyCount = 0L;

    public static Board create(Member member, String title, String content) {
        Board board = new Board();
        board.member = member;
        board.title = title;
        board.content = content;
        board.viewCount = 0L;
        board.replyCount = 0L;
        board.createdDate = LocalDateTime.now();
        return board;
    }

    public void incrementViewCount(){
        this.viewCount++;
    }
}
