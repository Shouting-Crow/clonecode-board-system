package com.clonecode.boardweb.service.board;

import com.clonecode.boardweb.domain.Board;
import com.clonecode.boardweb.domain.Member;
import com.clonecode.boardweb.domain.Reply;
import com.clonecode.boardweb.dto.board.*;
import com.clonecode.boardweb.dto.reply.ReplyDto;
import com.clonecode.boardweb.repository.BoardRepository;
import com.clonecode.boardweb.repository.MemberRepository;
import com.clonecode.boardweb.repository.ReplyRepository;
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
    private final ReplyRepository replyRepository;

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
    public Page<BoardListDto> searchBoards(BoardSearchDto dto, Pageable pageable) {
        return boardRepository.search(dto, pageable)
                .map(this::convertToDto);
    }

    @Override
    @Transactional
    public Board registerBoard(BoardRegisterDto boardRegisterDto) {
        Member member = memberRepository.findByLoginId(boardRegisterDto.getMember().getLoginId())
                .orElseThrow(() -> new IllegalStateException("회원이 존재하지 않습니다."));

        Board board = Board.create(member, boardRegisterDto.getTitle(), boardRegisterDto.getContent());
        return boardRepository.save(board);
    }

    @Override
    @Transactional
    public BoardDetailDto getBoardDetail(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        board.incrementViewCount();

        BoardDetailDto boardDetailDto = convertToDetailDto(board);

        List<ReplyDto> replyDtos = board.getReply().stream()
                .map(reply -> {
                    ReplyDto replyDto = new ReplyDto();
                    replyDto.setReplyId(reply.getId());
                    replyDto.setMember(reply.getMember());
                    replyDto.setContent(reply.getContent());
                    replyDto.setCreatedDate(reply.getCreatedDate());
                    return replyDto;
                })
                .toList();

        boardDetailDto.setReplies(replyDtos);

        return boardDetailDto;
    }

    @Override
    @Transactional
    public void updateBoard(BoardUpdateDto dto) {
        Board board = boardRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        boardRepository.save(board);
    }

    @Override
    @Transactional
    public void deleteBoard(Long boardId) {
        List<Reply> replies = replyRepository.findByBoardId(boardId);
        replyRepository.deleteAll(replies);

        boardRepository.deleteById(boardId);
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

    private BoardDetailDto convertToDetailDto(Board board){
        BoardDetailDto dto = new BoardDetailDto();
        dto.setId(board.getId());
        dto.setMember(board.getMember());
        dto.setTitle(board.getTitle());
        dto.setContent(board.getContent());
        dto.setViewCount(board.getViewCount());
        dto.setCreatedDate(board.getCreatedDate());

        return dto;
    }
}
