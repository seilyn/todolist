package com.io.todolist;

import com.io.todolist.account.dto.AccountReqDto;
import com.io.todolist.account.dto.AccountResDto;
import com.io.todolist.account.entity.Users;
import com.io.todolist.account.repository.UserRepository;
import com.io.todolist.account.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;

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

    @Test
    @DisplayName("유저 인증키 갱신 테스트")
    @Order(3)
    void Refresh_AccessKey_Test() {
        AccountReqDto.Login request = AccountReqDto.Login.builder()
                .userName("testuser1")
                .password("test")
                .build();

        AccountResDto.Login response = userService.logIn(request);

        Long userId = response.getUserId();

        Users user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());

        AccountReqDto.Refresh requestRefresh = AccountReqDto.Refresh.builder()
                .userName(user.getUserName())
                .build();

        // 현재 키를 가져 옵니다.
        String currentKey = user.getAuthKey();

        userService.refreshAuthKey(userId, requestRefresh);

        // 갱신된 사용자 객체를 다시 조회하여 업데이트된 상태를 가져옵니다.
        Users updatedUser = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        String nextKey = updatedUser.getAuthKey();


        assertThat(currentKey).isNotEqualTo(nextKey);

//        System.out.println("current : " + currentKey);
//        System.out.println("next: " + nextKey);
    }



}