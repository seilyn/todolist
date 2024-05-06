package com.io.todolist.application.service;

import com.io.todolist.application.dto.MateReqDto;
import com.io.todolist.application.dto.MateResDto;
import com.io.todolist.domain.Mate;
import com.io.todolist.domain.MateUser;
import com.io.todolist.domain.Users;
import com.io.todolist.infrasturcture.persistence.MateRepository;
import com.io.todolist.infrasturcture.persistence.MateUserRepository;
import com.io.todolist.infrasturcture.persistence.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class MateService {

    private UserRepository userRepository;

    private MateRepository mateRepository;

    private MateUserRepository mateUserRepository;

    public MateService(UserRepository userRepository, MateRepository mateRepository, MateUserRepository mateUserRepository) {
        this.userRepository = userRepository;
        this.mateRepository = mateRepository;
        this.mateUserRepository = mateUserRepository;
    }

    @Transactional
    public MateResDto.MateInfo createMate(Long userId, MateReqDto.CreateMate request) {
        if (mateRepository.existsByMateName(request.getMateName())) {
            log.error("동일한 그룹명이 존재합니다.");
            throw new RuntimeException();
        }

        Mate mate = Mate.builder()
                .mateName(request.getMateName())
                .inviteKey(UUID.randomUUID().toString())
                .build();

        mateRepository.save(mate);

        // 사용자 정보 가져오기
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // MateUser 엔티티 생성 및 저장
        MateUser mateUser = new MateUser();
        mateUser.setMate(mate);
        mateUser.setUser(user);
        mateUserRepository.save(mateUser);

        return MateResDto.MateInfo.of(mate);
    }

    @Transactional
    public void joinMate(String inviteKey, Long userId) {
        // Mate 조회
        Mate mate = mateRepository.findByInviteKey(inviteKey);
        if (mate == null) {
            throw new RuntimeException("초대코드가 올바르지 않습니다.");
        }

        // 사용자 조회
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 이미 해당 Mate에 속한 사용자인지 확인
        if (mateUserRepository.findByMateAndUser(mate, user) != null) {
            throw new RuntimeException("이미 해당 그룹에 속해 있습니다.");
        }

        // MateUser 생성 및 저장
        MateUser mateUser = new MateUser();
        mateUser.setMate(mate);
        mateUser.setUser(user);
        mateUserRepository.save(mateUser);
    }
}