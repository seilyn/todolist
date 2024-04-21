package com.io.todolist.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AccountReqDto {
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignUp{

        @NotNull
        @Size(min = 3, max = 50)
        private String username;

        @NotNull
        @Size(max = 100)
        private String password;

        @NotNull
        @Size(min = 5, max = 100)
        private String nickname;

        @NotNull
        private String emailAddress;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Login {
        @NotNull
        private String userName;

        @NotNull
        private String password;

    }



}
