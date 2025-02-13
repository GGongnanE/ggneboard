package com.ggne.ggneboard.service;

import com.ggne.ggneboard.dto.UserDTO;
import com.ggne.ggneboard.entity.User;
import com.ggne.ggneboard.repository.UserRepository;
import org.springframework.stereotype.Service;

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
}
