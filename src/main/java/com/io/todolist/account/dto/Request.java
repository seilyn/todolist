package com.io.todolist.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

public class Request {
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

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Refresh {
        @NotNull
        private String userName;
    }

    /**
     * 유저 이름을 가지고 와서 Mate 그룹을 만듬.
     */
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Mate {
        @NotNull
        private String userName;
    }
}
