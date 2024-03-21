package com.io.todolist.account.controller;

import com.io.todolist.account.dto.Request;
import com.io.todolist.account.dto.Response;
import com.io.todolist.account.service.UserService;
import com.io.todolist.common.dto.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/app")
public class ClientController {

    private final UserService userService;

    public ClientController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 유저 회원가입
     * @param request
     * @return
     */
    @PostMapping("/users")
    public ResponseEntity<CommonResponse> signup(@RequestBody Request.SignUp request) {
        Response.UserInfo userInfo = userService.signup(request);

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
    public ResponseEntity<CommonResponse> login(@RequestBody Request.Login request) {
        Response.Login loginInfo = userService.logIn(request);

        CommonResponse response =CommonResponse.builder()
                .success(true)
                .response(loginInfo)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("refresh/{id}")
    public ResponseEntity<CommonResponse> refreshAuthKey(@PathVariable("id") Long id, @RequestBody Request.Refresh request) {
        Response.AuthKeyInfo authKeyInfo = userService.refreshAuthKey(id, request);
        CommonResponse response =CommonResponse.builder()
                .success(true)
                .response(authKeyInfo)
                .build();

        return ResponseEntity.ok(response);
    }



}
