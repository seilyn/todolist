package com.io.todolist.domain;

import com.io.todolist.domain.Mate;
import com.io.todolist.domain.Users;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long task_id;

    @Column(name = "contents")
    private String contents;

    @Column(name = "completed")
    private Boolean isCompleted;

    @Column(name = "deadline")
    private String deadline;
    @Column(name = "author")
    private String author;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mate_id")
    private Mate mate;

    @Builder
    public Task( String contents, Boolean isCompleted, String deadline, String author) {
        this.contents = contents;
        this.isCompleted = isCompleted;
        this.author = author;
        this.deadline = deadline;
    }

}
