package com.io.todolist.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "user_task")
public class UserTask extends BaseTimeStamp{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;

    public void setUser(Users user) {
        this.user = user;
        user.getUserTasks().add(this);
    }

    public void setTask(Task task) {
        this.task = task;
        task.getUserTasks().add(this);
    }
}
