package com.io.todolist.application.dto;

import com.io.todolist.domain.Mate;
import lombok.Builder;
import lombok.Data;

public class MateResDto {
    @Builder
    @Data
    public static class MateInfo {

        private String mateName;

        public static MateResDto.MateInfo of(Mate mate) {
            if (mate == null) {
                return null;
            }

            return MateInfo.builder()
                    .mateName(mate.getMateName())
                    .build();
        }
    }
}
