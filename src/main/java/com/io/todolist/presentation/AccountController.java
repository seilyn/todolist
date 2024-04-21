package com.io.todolist.presentation;

import com.io.todolist.application.dto.AccountReqDto;
import com.io.todolist.application.dto.AccountResDto;
import com.io.todolist.application.service.UserService;
import com.io.todolist.application.dto.CommonResDto;
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
    public ResponseEntity<CommonResDto> signup(@RequestBody AccountReqDto.SignUp request) {
        AccountResDto.UserInfo userInfo = userService.signup(request);

        CommonResDto response = CommonResDto.builder()
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
    public ResponseEntity<CommonResDto> login(@RequestBody AccountReqDto.Login request) {
        AccountResDto.Login loginInfo = userService.logIn(request);

        CommonResDto response = CommonResDto.builder()
                .success(true)
                .response(loginInfo)
                .build();

        return ResponseEntity.ok(response);
    }




}
