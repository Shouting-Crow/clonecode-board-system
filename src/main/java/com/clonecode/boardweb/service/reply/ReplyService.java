package com.clonecode.boardweb.service.reply;

import com.clonecode.boardweb.domain.Reply;
import com.clonecode.boardweb.dto.reply.ReplyRegisterDto;
import com.clonecode.boardweb.dto.reply.ReplyUpdateDto;

public interface ReplyService {
    Reply registerReply(ReplyRegisterDto dto);
    Long updateReply(ReplyUpdateDto dto);
    Long deleteReply(Long id);
}
