package com.clonecode.boardweb.service.board;

import com.clonecode.boardweb.domain.Board;
import com.clonecode.boardweb.domain.Member;
import com.clonecode.boardweb.dto.board.BoardListDto;
import com.clonecode.boardweb.dto.board.BoardRegisterDto;
import com.clonecode.boardweb.repository.BoardRepository;
import com.clonecode.boardweb.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<BoardListDto> getAllBoards() {

        List<Board> boards = boardRepository.findAll();

        return boards.stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public Page<BoardListDto> getAllBoardsByPaging(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        return boards.map(this::convertToDto);
    }

    @Override
    @Transactional
    public Board registerBoard(BoardRegisterDto boardRegisterDto) {
        Member member = memberRepository.findByLoginId(boardRegisterDto.getMember().getLoginId())
                .orElseThrow(() -> new IllegalStateException("회원이 존재하지 않습니다."));

        Board board = Board.create(member, boardRegisterDto.getTitle(), boardRegisterDto.getContent());
        return boardRepository.save(board);
    }

    private BoardListDto convertToDto(Board board){
        BoardListDto dto = new BoardListDto();
        dto.setId(board.getId());
        dto.setTitle(board.getTitle());
        dto.setCreatedDate(board.getCreatedDate());
        dto.setMemberName(board.getMember().getName());
        dto.setViewCount(board.getViewCount());
        dto.setReplyCount((long) board.getReply().size());

        return dto;
    }
}
