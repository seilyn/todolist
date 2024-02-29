package com.io.todolist.account.controller;

import com.io.todolist.account.dto.Request;
import com.io.todolist.account.dto.Response;
import com.io.todolist.account.service.UserService;
import com.io.todolist.common.dto.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/app")
public class ClientController {

    private final UserService userService;

    public ClientController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<CommonResponse> signup(@RequestBody Request.SignUp request) {
        Response.UserInfo userInfo = userService.signup(request);

        CommonResponse response = CommonResponse.builder()
                .success(true)
                .response(userInfo)
                .build();

        return ResponseEntity.ok(response);
    }

}