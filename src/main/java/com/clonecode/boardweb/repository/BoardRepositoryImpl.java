package com.clonecode.boardweb.repository;

import com.clonecode.boardweb.domain.Board;
import com.clonecode.boardweb.domain.QBoard;
import com.clonecode.boardweb.dto.board.BoardSearchDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BoardRepositoryImpl implements BoardRepositoryCustom{

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public Page<Board> search(BoardSearchDto dto, Pageable pageable) {
        QBoard board = QBoard.board;
        BooleanBuilder builder = new BooleanBuilder();

        if (dto.getTitle() != null){
            builder.and(board.title.contains(dto.getTitle()));
        }
        if (dto.getContent() != null){
            builder.and(board.content.contains(dto.getContent()));
        }
        if (dto.getMemberName() != null){
            builder.and(board.member.name.contains(dto.getMemberName()));
        }

        List<Board> results = queryFactory.selectFrom(board)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory.selectFrom(board)
                .where(builder)
                .fetch().size();

        return new PageImpl<>(results, pageable, total);
    }
}
