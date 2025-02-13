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

    @GetMapping
    public String test() {
        return "test";
    }

    // 사용자 생성
    @PostMapping
    public User createUser(@RequestBody UserDTO userDTO) {
        log.info("Input User's data : {}", userDTO);

        // role 값이 null일 경우 기본 USER 설정
        UserRoleEnum role = userDTO.getRole() != null ?
                            UserRoleEnum.valueOf(userDTO.getRole()) : UserRoleEnum.USER;

        User userObj = new User(
                userDTO.getAccount(),
                userDTO.getPassword(),
                userDTO.getEmail(),
                userDTO.getUsername(),
                role
        );

        return userService.createUser(userObj);
    }
}
