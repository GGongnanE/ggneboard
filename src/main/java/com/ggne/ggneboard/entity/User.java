package com.ggne.ggneboard.entity;

import com.ggne.ggneboard.config.UserRoleEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DB Table name is 'users'
 * (Because 'User' is reserved word of H2 database is case-insensitive)
 *
 * @author GGNE
 * @since 2025.02.13
 */
@Getter
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String account;         // 계정명 (로그인에 필요한 고유 값)

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;        // 사용자 이름

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder
    public User(String account, String password, String username, String email, UserRoleEnum role) {
        this.account = account;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}