package com.io.todolist.account.repository;

import com.io.todolist.account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserName(String userName);
}
