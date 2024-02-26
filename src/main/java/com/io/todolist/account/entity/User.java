package com.io.todolist.account.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 추후 토큰 인증 로직 추가
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @OneToMany(mappedBy = "user")
    private List<Task> taskList;

    @Builder
    public User(String userName, String password, String emailAddress, String nickname) {
        this.userName = userName;
        this.password = password;
        this.emailAddress = emailAddress;
        this.nickname = nickname;
    }
}
