package com.io.todolist.account.service.impl;

import com.io.todolist.account.dto.AccountReqDto;
import com.io.todolist.account.dto.AccountResDto;
import com.io.todolist.account.entity.Users;
import com.io.todolist.account.repository.UserRepository;
import com.io.todolist.account.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.UUID;

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
                .joinedDate(new Date())
                .authKey(UUID.randomUUID().toString())
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

    /**
     * 인증키 갱신
     * @param id
     * @param request
     * @return AccountResDto.AuthKeyInfo ( 갱신된 인증키 반환 )
     */
    @Override
    @Transactional
    public AccountResDto.AuthKeyInfo refreshAuthKey(Long id, AccountReqDto.Refresh request) {
        Users user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException());

        // 새로운 Key 발급
        String newAuthKey = UUID.randomUUID().toString();

        // TODO: Auth key 중복 체크 로직 v

        user.refreshAuthKey(newAuthKey);

        AccountResDto.AuthKeyInfo info = AccountResDto.AuthKeyInfo.builder()
                .authKey(newAuthKey)
                .build();

        return info;
    }


}
