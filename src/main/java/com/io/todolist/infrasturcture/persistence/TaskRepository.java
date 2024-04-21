package com.io.todolist.infrasturcture.persistence;

import com.io.todolist.domain.Task;
import com.io.todolist.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByAuthor(String userName);

    List<Task> findTaskByUser(Users user);
}
