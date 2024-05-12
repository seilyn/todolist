package com.io.todolist.infrasturcture.persistence;

import com.io.todolist.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface UserRepository extends JpaRepository<Users, Long> {
    /**
     * 유저 중복 체크
     * @param userName 유저 아이디
     * @return Boolean ( 있으면 True, 없으면 False)
     */
    boolean existsByUserName(String userName);

    /**
     * 아이디로 유저 찾기
     * @param userName
     * @return
     */
    Users findByUserName(String userName);

}
