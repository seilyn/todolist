package com.io.todolist.account.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Task {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 20)
    private String title;
    private String contents;

    @Column(name = "completed")
    private Boolean isCompleted;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mate_id")
    private Mate mate;

    @Builder
    public Task(Long id, String title, String contents, Boolean isCompleted, User user, LocalDateTime deadline) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.isCompleted = isCompleted;
        this.user = user;
        this.deadline = deadline;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDeadline(LocalDateTime parseDatetime) {
        this.deadline = parseDatetime;
    }
}
