package com.ggne.ggneboard.controller;

import com.ggne.ggneboard.config.UserRoleEnum;
import com.ggne.ggneboard.dto.UserDTO;
import com.ggne.ggneboard.entity.User;
import com.ggne.ggneboard.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // 생성자 기반 의존성 주입
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 사용자 조회
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // 사용자 정보 삭제
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // 사용자 생성
    @PostMapping
    public User createUser(@RequestBody UserDTO userDTO) {
        log.info("Input User's data : {}", userDTO);

        // role 값이 null일 경우 기본 USER 설정
        UserRoleEnum role = userDTO.getRole() != null ?
                            UserRoleEnum.valueOf(userDTO.getRole()) : UserRoleEnum.USER;

        User userObj = new User().builder()
                            .account(userDTO.getAccount())
                            .password(userDTO.getPassword())
                            .email(userDTO.getEmail())
                            .username(userDTO.getUsername())
                            .role(role)
                            .build();

        return userService.createUser(userObj);
    }
}
