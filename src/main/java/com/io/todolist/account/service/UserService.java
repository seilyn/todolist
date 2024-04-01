package com.io.todolist.account.service;

import com.io.todolist.account.dto.AccountReqDto;
import com.io.todolist.account.dto.AccountResDto;

public interface UserService {
    AccountResDto.UserInfo signup(AccountReqDto.SignUp request);

    AccountResDto.Login logIn(AccountReqDto.Login request);

    AccountResDto.MateInfo createMate(AccountReqDto.CreateMate request);
}
