package com.io.todolist;

import com.io.todolist.application.dto.AccountReqDto;
import com.io.todolist.application.dto.AccountResDto;
import com.io.todolist.infrasturcture.persistence.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 회원가입 테스트")
    @Order(1)
    public void UserSignUp_Test() {
        /*
        Given
         */
        AccountReqDto.SignUp request = AccountReqDto.SignUp.builder()
                .username("testuser1")
                .nickname("test")
                .password("test")
                .emailAddress("test@test.com")
                .build();
        /*
        when
         */
        AccountResDto.UserInfo response = userService.signup(request);

        /*
        then
         */
        assertThat(response.getUserName()).isEqualTo(request.getUsername());
        assertThat(response.getNickname()).isEqualTo(request.getNickname());
        assertThat(response.getEmailAddress()).isEqualTo(request.getEmailAddress());
    }


    @Test
    @DisplayName("유저 로그인 성공 테스트")
    @Order(2)
    void UserLogin_Test() {
        AccountReqDto.Login request = AccountReqDto.Login.builder()
                .userName("testuser1")
                .password("test")
                .build();

        AccountResDto.Login response = userService.logIn(request);

        assertThat(response.getUserName()).isEqualTo(request.getUserName());
    }



}