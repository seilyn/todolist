package com.io.todolist.domain;

import com.io.todolist.domain.Mate;
import com.io.todolist.domain.Users;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(indexes = @Index(columnList = "task_id", name = "idx_task_id"))
public class Task extends BaseTimeStamp{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long task_id;

    /**
     * Task 내용
     */
    @Column(name = "contents")
    private String contents;

    /**
     * 완료여부
     */
    @Column(name = "completed")
    private Boolean isCompleted;

    /**
     * 마감기한
     */
    @Column(name = "deadline")
    private String deadline;

    /**
     * 작성자
     */
    @Column(name = "author")
    private String author;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Users user;

    @Builder
    public Task( String contents, Boolean isCompleted, String deadline, String author) {
        this.contents = contents;
        this.isCompleted = isCompleted;
        this.author = author;
        this.deadline = deadline;
    }

}
