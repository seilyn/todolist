package com.io.todolist.application.service;

import com.io.todolist.application.dto.MateReqDto;
import com.io.todolist.application.dto.MateResDto;

public interface MateService {
    MateResDto.MateInfo createMate(MateReqDto.CreateMate request);

}
