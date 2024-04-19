package com.io.todolist.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Long user_id;

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
     * 소속그룹
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "mate_id", nullable = true, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Mate mate;


    /**
     * Constructor
     * @param userName
     * @param password
     * @param emailAddress
     * @param nickname
     * @param activated
     * @param mate
     */
    @Builder
    public Users(String userName, String password, String emailAddress, String nickname, boolean activated, Mate mate) {
        this.userName = userName;
        this.password = password;
        this.emailAddress = emailAddress;
        this.nickname = nickname;
        this.activated = activated;
        this.mate = mate;
    }
}
