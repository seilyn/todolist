package com.io.todolist.presentation;

import com.io.todolist.application.dto.CommonResDto;
import com.io.todolist.application.dto.MateReqDto;
import com.io.todolist.application.dto.MateResDto;
import com.io.todolist.application.service.MateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/app")
public class MateController {

    private final MateService mateService;

    public MateController(MateService mateService) {
        this.mateService = mateService;
    }

    @PostMapping("/mate/{id}")
    public ResponseEntity<CommonResDto> createMate(@PathVariable Long id, @RequestBody MateReqDto.CreateMate request ){
        MateResDto.MateInfo mateInfo = mateService.createMate(id, request);

        CommonResDto response = CommonResDto.builder()
                .success(true)
                .response(mateInfo)
                .build();

        return ResponseEntity.ok(response);

    }

    @PostMapping("/mate/join/{id}")
    public ResponseEntity<CommonResDto> joinMate(@PathVariable Long id, @RequestBody MateReqDto.JoinMate request) {
        mateService.joinMate(request.getInviteKey(), id);

        CommonResDto response = CommonResDto.builder()
                .success(true)
                .response(true)
                .build();

        return ResponseEntity.ok(response);
    }
}
