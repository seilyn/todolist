package com.io.todolist.application.dto;

import com.io.todolist.domain.Users;
import lombok.Builder;
import lombok.Data;

public class AccountResDto {
    @Builder
    @Data
    public static class UserInfo {
        private String userName;

        private String nickname;

        private String emailAddress;

        public static AccountResDto.UserInfo of(Users user) {
            if (user == null) {
                return null;
            }

            return UserInfo.builder()
                    .userName(user.getUserName())
                    .nickname(user.getNickname())
                    .emailAddress(user.getEmailAddress())
                    .build();
        }
    }

    /**
     * 임시
     */
    @Builder
    @Data
    public static class Login {
        private Long userId;
        private String userName;
    }




}
