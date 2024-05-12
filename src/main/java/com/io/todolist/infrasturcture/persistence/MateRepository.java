package com.io.todolist.infrasturcture.persistence;

import com.io.todolist.domain.Mate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface MateRepository extends JpaRepository<Mate, Long> {
    boolean existsByMateName(String mateName);

    Mate findByInviteKey(String inviteKey);


}