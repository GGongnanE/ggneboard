package com.ggne.ggneboard.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 사용자 정보 DTO
 */

@Getter
@Setter
@ToString
public class UserDTO {

    private String account;
    private String password;
    private String email;
    private String username;
    private String role;
}
