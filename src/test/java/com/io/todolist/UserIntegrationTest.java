package com.io.todolist;

import com.io.todolist.application.dto.AccountReqDto;
import com.io.todolist.application.dto.AccountResDto;
import com.io.todolist.application.service.UserService;
import com.io.todolist.domain.Users;
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
        AccountReqDto.SignUp request1 = AccountReqDto.SignUp.builder()
                .username("테스트1")
                .nickname("test1")
                .password("test1")
                .emailAddress("test@test.com")
                .build();

        AccountReqDto.SignUp request2 = AccountReqDto.SignUp.builder()
                .username("테스트2")
                .nickname("test2")
                .password("test2")
                .emailAddress("test2@test.com")
                .build();
        /*
        when
         */
        AccountResDto.UserInfo response = userService.signup(request1);
        AccountResDto.UserInfo response2 = userService.signup(request2);

        /*
        then
         */
        assertThat(response.getUserName()).isEqualTo(request1.getUsername());
        assertThat(response.getNickname()).isEqualTo(request1.getNickname());
        assertThat(response.getEmailAddress()).isEqualTo(request1.getEmailAddress());
    }


    @Test
    @DisplayName("유저 로그인 성공 테스트")
    @Order(2)
    void UserLogin_Test() {
        AccountReqDto.Login request = AccountReqDto.Login.builder()
                .userName("테스트1")
                .password("test1")
                .build();

        AccountResDto.Login response = userService.logIn(request);

        assertThat(response.getUserName()).isEqualTo(request.getUserName());
    }




}