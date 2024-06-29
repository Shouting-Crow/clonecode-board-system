package com.clonecode.boardweb.service.board;

import com.clonecode.boardweb.domain.Board;
import com.clonecode.boardweb.domain.Member;
import com.clonecode.boardweb.domain.Reply;
import com.clonecode.boardweb.dto.board.BoardListDto;
import com.clonecode.boardweb.repository.MemberRepository;
import com.clonecode.boardweb.service.register.MemberRegisterService;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private MemberRegisterService memberRegisterService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    void boardListTest(){
        Member member = memberRepository.findById(1L).get();

        Board board = new Board();
        board.setMember(member);
        board.setTitle("새로운 게시글");
        board.setReply(List.of(new Reply()));
        board.setViewCount(1L);
        board.setCreatedDate(LocalDateTime.now());

        em.persist(board);

        List<BoardListDto> boards = boardService.getAllBoards();
        assertThat(boards).isNotEmpty();

        for (BoardListDto b : boards) {
            System.out.println("board id = " + b.getId());
            System.out.println("Member name  = " + b.getMemberName());
            System.out.println("reply count = " + b.getReplyCount());
            System.out.println("title = " + b.getTitle());
            System.out.println("created date = " + b.getCreatedDate());
            System.out.println("view count = " + b.getViewCount());
        }

    }

    @Test
    @Transactional
    void pagingTest(){
        Member member = memberRepository.findById(1L).get();

        Board board = new Board();
        board.setMember(member);
        board.setTitle("새로운 게시글");
        board.setReply(List.of(new Reply()));
        board.setViewCount(1L);
        board.setCreatedDate(LocalDateTime.now());

        em.persist(board);

        Pageable pageable = PageRequest.of(0, 10);
        Page<BoardListDto> boards = boardService.getAllBoardsByPaging(pageable);

        assertThat(boards).isNotEmpty();

        for (BoardListDto b : boards) {
            System.out.println("board id = " + b.getId());
            System.out.println("Member name  = " + b.getMemberName());
            System.out.println("reply count = " + b.getReplyCount());
            System.out.println("title = " + b.getTitle());
            System.out.println("created date = " + b.getCreatedDate());
            System.out.println("view count = " + b.getViewCount());
        }

    }

}