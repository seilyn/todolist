package com.io.todolist.application.service;

import com.io.todolist.application.dto.AccountReqDto;
import com.io.todolist.application.dto.AccountResDto;

public interface UserService {
    AccountResDto.UserInfo signup(AccountReqDto.SignUp request);

    AccountResDto.Login logIn(AccountReqDto.Login request);

}
