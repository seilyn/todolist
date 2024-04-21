package com.io.todolist.infrasturcture.persistence;

import com.io.todolist.domain.Mate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateRepository extends JpaRepository<Mate, Long> {
    boolean existsByMateName(String mateName);

    Mate findByInviteKey(String inviteKey);
}