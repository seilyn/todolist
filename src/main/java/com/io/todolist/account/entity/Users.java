package com.io.todolist.account.entity;

import com.io.todolist.task.entity.Task;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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

    @Column(name = "joined_date")
    private Date joinedDate;

    @Column(name = "have_mate")
    private Boolean haveMate;

    @ManyToOne
    @JoinColumn(name = "mates_id")
    private Mate mate;

    @Builder
    public Users(String userName, String password, String emailAddress, String nickname, Date joinedDate, Boolean haveMate) {
        this.userName = userName;
        this.password = password;
        this.emailAddress = emailAddress;
        this.nickname = nickname;
        this.joinedDate = joinedDate;
        this.haveMate = haveMate;
    }
}
