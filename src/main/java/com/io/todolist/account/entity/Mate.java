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

    @Column(name = "mate_name", nullable = false)
    private String mateName;

    @OneToMany(mappedBy = "mate", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Users> users;

}

