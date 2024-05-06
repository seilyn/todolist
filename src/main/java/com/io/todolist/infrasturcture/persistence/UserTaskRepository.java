package com.io.todolist.infrasturcture.persistence;

import com.io.todolist.domain.UserTask;
import com.io.todolist.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTaskRepository extends JpaRepository<UserTask, Long> {
    List<UserTask> findByUser(Users user);
}
