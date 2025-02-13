package com.ggne.ggneboard.service;

import com.ggne.ggneboard.dto.UserDTO;
import com.ggne.ggneboard.entity.User;
import com.ggne.ggneboard.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    // 생성자 기반 의존성 주입
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 사용자 생성
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // 사용자 조회
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID : " + id  + " 해당 사용자가 존재하지 않습니다."));
    }

    // 사용자 정보 삭제
    public void deleteUser(Long id) {

        // 유저 정보 조회 후, 있으면 삭제
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID : " + id  + " 해당 사용자가 존재하지 않습니다."));

        userRepository.delete(user);
        log.info("ID : {}, 사용자 정보 삭제 완료", id);
    }

    // 사용자 정보 수정
    public User updateUser(Long id, UserDTO userDTO) {

        // 유저 정보 조회 후, 있으면 수정
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID : " + id  + " 해당 사용자가 존재하지 않습니다."));

        user.update(userDTO);
        return userRepository.save(user);
    }
}
