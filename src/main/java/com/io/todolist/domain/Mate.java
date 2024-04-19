package com.io.todolist.domain;

import com.io.todolist.domain.Users;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "mate")
public class Mate extends BaseTimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mate_id;

    /**
     *
     */
    @Column(name = "leader_name")
    private String leaderName;

    /**
     * Mate 비밀번호
     */
    @Column(name = "password")
    private String password;

    /**
     * Mate 이름
     */
    @Column(name = "mate_name")
    private String mateName;

}

