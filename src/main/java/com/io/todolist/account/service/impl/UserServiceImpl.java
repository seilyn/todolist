package com.io.todolist.account.service.impl;

import com.io.todolist.account.dto.Request;
import com.io.todolist.account.dto.Response;
import com.io.todolist.account.entity.User;
import com.io.todolist.account.repository.UserRepository;
import com.io.todolist.account.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, PasswordEncoder passwordEncoder1) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder1;
    }

    /**
     * 회원 추가 메소드
     * @param request Request.SignUp
     * @return
     */
    @Override
    @Transactional
    public Response.UserInfo signup(Request.SignUp request) {
        if (userRepository.existsByUserName(request.getUsername())) {
            // TODO: 추후 Error를 컨트롤하는 ErrorResponse 로직을 구현해야 합니다.
            // 현재로서는 로그만 남기고 함수 종료
            log.error("이미 존재하는 닉네임입니다. 다른 닉네임을 사용해주세요.");
            throw new RuntimeException();
        }

        User user = User.builder()
                .userName(request.getUsername())
                // 비밀번호 암호화
                .password(passwordEncoder.encode(request.getPassword()))
                .emailAddress(request.getEmailAddress())
                .nickname(request.getEmailAddress())
                .build();

        log.info(request.getUsername(),
                request.getPassword(),
                request.getEmailAddress(),
                request.getNickname());

        return Response.UserInfo.of(userRepository.save(user));
    }
}
