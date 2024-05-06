package com.io.todolist.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.expression.spel.ast.Assign;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(indexes = @Index(columnList = "id", name = "idx_task_id"))
public class Task extends BaseTimeStamp{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToMany(mappedBy = "task")
    private List<UserTask> userTasks = new ArrayList<>();


    @Builder
    public Task(String contents, Boolean isCompleted, String deadline, String author) {
        this.contents = contents;
        this.isCompleted = isCompleted;
        this.author = author;
        this.deadline = deadline;
    }

}
