package com.io.todolist.account.dto;

import com.io.todolist.account.entity.User;
import lombok.Builder;
import lombok.Data;

public class Response {
    @Builder
    @Data
    public static class UserInfo {
        private String userName;

        private String nickname;

        private String emailAddress;

        public static Response.UserInfo of(User user) {
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
}
