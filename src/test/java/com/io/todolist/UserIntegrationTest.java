package com.io.todolist;

import com.io.todolist.account.dto.Request;
import com.io.todolist.account.dto.Response;
import com.io.todolist.account.repository.UserRepository;
import com.io.todolist.account.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 회원가입 테스트")
    public void UserSignUp_Test() {
        /*
        Given
         */
        Request.SignUp request = Request.SignUp.builder()
                .username("testuser")
                .nickname("test")
                .password("test")
                .emailAddress("test@test.com")
                .build();
        /*
        when
         */
        Response.UserInfo response = userService.signup(request);


        /*
        then
         */
        assertThat(response.getUserName()).isEqualTo(request.getUsername());
        assertThat(response.getNickname()).isEqualTo(request.getNickname());
        assertThat(response.getEmailAddress()).isEqualTo(request.getEmailAddress());
    }


}