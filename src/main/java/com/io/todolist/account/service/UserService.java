package com.io.todolist.account.service;

import com.io.todolist.account.dto.Request;
import com.io.todolist.account.dto.Response;

public interface UserService {
    Response.UserInfo signup(Request.SignUp request);

    Response.Login logIn(Request.Login request);
}
