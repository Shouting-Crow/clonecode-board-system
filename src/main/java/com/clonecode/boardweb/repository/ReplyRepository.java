package com.clonecode.boardweb.repository;

import com.clonecode.boardweb.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
