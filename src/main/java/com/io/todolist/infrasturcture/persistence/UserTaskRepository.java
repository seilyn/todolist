package com.io.todolist.infrasturcture.persistence;

import com.io.todolist.domain.UserTask;
import com.io.todolist.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserTaskRepository extends JpaRepository<UserTask, Long> {
    List<UserTask> findByUser(Users user);

    @Query(value = "SELECT ut.task.id FROM UserTask ut WHERE ut.user.id = :userId")
    List<Long> findUserTasksByUserId(@Param("userId") Long userId);
}
