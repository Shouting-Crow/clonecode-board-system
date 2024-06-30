package com.clonecode.boardweb;

import com.clonecode.boardweb.domain.Board;
import com.clonecode.boardweb.domain.Member;
import com.clonecode.boardweb.repository.BoardRepository;
import com.clonecode.boardweb.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

//@Component
//public class DataLoader {
//
//    @Bean
//    CommandLineRunner initDatabase(BoardRepository boardRepository, MemberRepository memberRepository){
//        return args -> {
//            Member member1 = memberRepository.findByLoginId("member").orElseThrow();
//            Member member2 = memberRepository.findByLoginId("newmember").orElseThrow();
//
//            boardRepository.save(Board.create(member1, "첫 게시글", null, 1L));
//            boardRepository.save(Board.create(member2, "두 번째 게시글", null, 2L));
//            boardRepository.save(Board.create(member1, "세 번째 게시글", null, 3L));
//        };
//    }
//}
