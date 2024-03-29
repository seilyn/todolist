package com.io.todolist.account.repository;

import com.io.todolist.account.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

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
