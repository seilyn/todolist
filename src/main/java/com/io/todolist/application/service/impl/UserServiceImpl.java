package com.io.todolist.application.service.impl;

import com.io.todolist.application.dto.AccountReqDto;
import com.io.todolist.application.dto.AccountResDto;
import com.io.todolist.domain.Users;
import com.io.todolist.infrasturcture.persistence.MateRepository;
import com.io.todolist.infrasturcture.persistence.UserRepository;
import com.io.todolist.application.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final MateRepository mateRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, PasswordEncoder passwordEncoder1,
                           MateRepository mateRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder1;
        this.mateRepository = mateRepository;
    }

    /**
     * 회원 추가 메소드
     * @param request Request.SignUp
     * @return
     */
    @Override
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
                // 비밀번호 암호화
                .password(passwordEncoder.encode(request.getPassword()))
                .emailAddress(request.getEmailAddress())
                .nickname(request.getNickname())
                .haveMate(false)
                .joinedDate(new Date())
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
    @Override
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
                .userId(findUser.getUserId())
                .userName(request.getUserName())
                .build();

         return login;
    }




}
