package com.io.todolist.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 추후 토큰 인증 로직 추가
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(columnList = "userName", name = "idx_user_name"), name = "users")
public class Users extends BaseTimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 유저명
     */
    @Column(name = "user_name", nullable = false)
    private String userName;

    /**
     * 비밀번호
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * 이메일주소
     */
    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    /**
     * 닉네임
     */
    @Column(name = "nickname", nullable = false)
    private String nickname;

    /**
     * 계정활성화여부
     */
    @Column(name = "activated", nullable = false)
    private boolean activated;

    /**
     * Task 리스트
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();


    @OneToMany(mappedBy = "user")
    private List<MateUser> mateUsers = new ArrayList<>();
    @Builder
    public Users(String userName, String password, String emailAddress, String nickname, boolean activated) {
        this.userName = userName;
        this.password = password;
        this.emailAddress = emailAddress;
        this.nickname = nickname;
        this.activated = activated;
    }

}
