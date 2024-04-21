package com.io.todolist.infrasturcture.persistence;

import com.io.todolist.domain.Mate;
import com.io.todolist.domain.MateUser;
import com.io.todolist.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MateUserRepository extends JpaRepository<MateUser, Long> {
    MateUser findByMateAndUser(Mate mate, Users user);

    List<MateUser> findByUser(Users user);

    List<MateUser> findByMateAndUserNot(Mate mate, Users user);
}
