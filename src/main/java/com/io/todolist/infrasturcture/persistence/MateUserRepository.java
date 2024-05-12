package com.io.todolist.infrasturcture.persistence;

import com.io.todolist.domain.Mate;
import com.io.todolist.domain.MateUser;
import com.io.todolist.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface MateUserRepository extends JpaRepository<MateUser, Long> {

    MateUser findByMateAndUser(Mate mate, Users user);

    @Query(value = "SELECT user_id FROM mate_user WHERE mate_id = :mateId", nativeQuery = true)
    List<Long> findUserIdsByMateId(@Param("mateId") Long mateId);

    List<MateUser> findMateByUserId(Long userId);




}
