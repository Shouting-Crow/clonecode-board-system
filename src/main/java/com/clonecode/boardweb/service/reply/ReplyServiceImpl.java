package com.clonecode.boardweb.service.reply;

import com.clonecode.boardweb.domain.Board;
import com.clonecode.boardweb.domain.Member;
import com.clonecode.boardweb.domain.Reply;
import com.clonecode.boardweb.dto.reply.ReplyRegisterDto;
import com.clonecode.boardweb.dto.reply.ReplyUpdateDto;
import com.clonecode.boardweb.repository.BoardRepository;
import com.clonecode.boardweb.repository.MemberRepository;
import com.clonecode.boardweb.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Reply registerReply(ReplyRegisterDto dto) {
        Board board = boardRepository.findById(dto.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));
        Member member = memberRepository.findByLoginId(dto.getMember().getLoginId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Reply reply = new Reply();
        reply.setContent(dto.getContent());
        reply.setBoard(board);
        reply.setMember(member);
        reply.setCreatedDate(LocalDateTime.now());

        return replyRepository.save(reply);
    }

    @Override
    @Transactional
    public Long updateReply(ReplyUpdateDto dto) {
        Reply reply = replyRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        reply.setContent(dto.getContent());
        reply.setCreatedDate(dto.getUpdatedDate());
        replyRepository.save(reply);

        return reply.getBoard().getId();
    }

    @Override
    @Transactional
    public Long deleteReply(Long id) {
        Reply reply = replyRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        replyRepository.deleteById(id);

        return reply.getBoard().getId();
    }

}
