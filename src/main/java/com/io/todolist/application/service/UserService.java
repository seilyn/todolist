package com.io.todolist.application.service;

import com.io.todolist.application.dto.AccountReqDto;
import com.io.todolist.application.dto.AccountResDto;
import com.io.todolist.domain.Task;
import com.io.todolist.domain.Users;
import com.io.todolist.infrasturcture.persistence.TaskRepository;
import com.io.todolist.infrasturcture.persistence.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;


    private final TaskRepository taskRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, TaskRepository taskRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 회원 추가 메소드
     * @param request Request.SignUp
     * @return
     */
    @Transactional
    public AccountResDto.UserInfo signup(AccountReqDto.SignUp request) {
        if (userRepository.existsByUserName(request.getUsername())) {
            // TODO: 추후 Error를 컨트롤하는 ErrorResponse 로직을 구현해야 합니다.
            // 현재로서는 로그만 남기고 함수 종료
            log.error("이미 존재하는 닉네임입니다. 다른 닉네임을 사용해주세요.");
            throw new RuntimeException();
        }

        Users user = Users.builder()

                .userName(request.getUsername())
                .activated(true)
                // 비밀번호 암호화
                .password(passwordEncoder.encode(request.getPassword()))
                .emailAddress(request.getEmailAddress())
                .nickname(request.getNickname())
                .build();

        log.info(request.getUsername(),
                request.getPassword(),
                request.getEmailAddress(),
                request.getNickname() + " -> 가입 요청");

        return AccountResDto.UserInfo.of(userRepository.save(user));
    }


    /**
     * 로그인 메소드
     * @param request Request.Login ( 아이디, 비밀번호)
     * @return Response.Login (유저 아이디를 return)
     * 추후에 토큰인증으로 변경할 메소드임.
     */
    public AccountResDto.Login logIn(AccountReqDto.Login request) {
         Users findUser = userRepository.findByUserName(request.getUserName());

         if (findUser.getUserName() == null) {
             log.error("아이디나 비밀번호가 맞지 않습니다.");
             return null;
         }
         if (!passwordEncoder.matches(request.getPassword(), findUser.getPassword())) {

             log.error("아이디나 비밀번호가 맞지 않습니다.");
             return null;
         }

        AccountResDto.Login login = AccountResDto.Login.builder()
                .userId(findUser.getId())
                .userName(request.getUserName())
                .build();

         return login;
    }

    @Transactional
    public void shareTasks(String inviteKey, Long userId) {

    }




}
