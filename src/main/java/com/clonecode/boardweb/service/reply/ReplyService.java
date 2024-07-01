package com.clonecode.boardweb.service.reply;

import com.clonecode.boardweb.domain.Reply;
import com.clonecode.boardweb.dto.reply.ReplyRegisterDto;

public interface ReplyService {
    Reply registerReply(ReplyRegisterDto dto);
}
