package com.io.todolist.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mate extends BaseTimeStamp{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Mate 이름
     */
    @Column(name = "mate_name")
    private String mateName;

    @Column(name = "invite_key")
    private String inviteKey;

    @OneToMany(mappedBy = "mate")
    private List<MateUser> mateUsers = new ArrayList<>();

    @Builder
    public Mate(Long id, String mateName, String inviteKey) {
        this.id = id;
        this.mateName = mateName;
        this.inviteKey = inviteKey;
    }
}
