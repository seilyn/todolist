package com.io.todolist.account.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 추후 토큰 인증 로직 추가
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

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

    @Column(name = "auth_key")
    private String authKey;

    @Column(name = "joined_date")
    private Date joinedDate;

    @Builder
    public Users(String userName, String password, String emailAddress, String nickname, String authKey, Date joinedDate) {
        this.userName = userName;
        this.password = password;
        this.emailAddress = emailAddress;
        this.nickname = nickname;
        this.authKey = authKey;
        this.joinedDate = joinedDate;
    }

    /**
     * 인증 키 갱신
     * @param authKey
     */
    public void refreshAuthKey(String authKey){
        this.authKey = authKey;
    }
}
