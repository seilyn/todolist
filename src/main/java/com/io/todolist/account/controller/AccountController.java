package com.io.todolist.account.controller;

import com.io.todolist.account.dto.AccountReqDto;
import com.io.todolist.account.dto.AccountResDto;
import com.io.todolist.account.entity.Users;
import com.io.todolist.account.service.UserService;
import com.io.todolist.common.dto.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/app")
public class AccountController {

    private final UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 유저 회원가입
     * @param request
     * @return
     */
    @PostMapping("/users")
    public ResponseEntity<CommonResponse> signup(@RequestBody AccountReqDto.SignUp request) {
        AccountResDto.UserInfo userInfo = userService.signup(request);

        CommonResponse response = CommonResponse.builder()
                .success(true)
                .response(userInfo)
                .build();

        return ResponseEntity.ok(response);
    }

    /**
     * 유저 로그인
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<CommonResponse> login(@RequestBody AccountReqDto.Login request) {
        AccountResDto.Login loginInfo = userService.logIn(request);

        CommonResponse response =CommonResponse.builder()
                .success(true)
                .response(loginInfo)
                .build();

        return ResponseEntity.ok(response);
    }


}
