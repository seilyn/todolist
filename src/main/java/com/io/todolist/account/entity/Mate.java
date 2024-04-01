package com.io.todolist.account.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Mate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "leader")
    private String leaderName;

    @Column(name = "invite_key")
    private String inviteKey;

    @Column(name = "mate_name")
    private String mateName;

    @OneToMany(mappedBy = "mate", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "mate_id")
    private Set<Users> users;

}

