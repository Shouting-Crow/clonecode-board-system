package com.clonecode.boardweb.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

    @NotEmpty
    private String name;

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

    @Embedded
    private Address address;

    @NotEmpty
    private String phoneNumber;

    @NotEmpty
    private String nickname;
}
