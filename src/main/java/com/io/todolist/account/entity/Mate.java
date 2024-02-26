package com.io.todolist.account.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mate {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "mate_name", nullable = false)
    private String mateName;

    @OneToMany(mappedBy = "mate")
    private List<Task> taskList;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Builder
    public Mate(String mateName, String userName) {
        this.mateName = mateName;
        this.userName = userName;
    }
}
