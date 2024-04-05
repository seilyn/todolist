package com.io.todolist.domain;

import com.io.todolist.domain.Users;
import jakarta.persistence.*;
import lombok.*;

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
    private Set<Users> users;

}

