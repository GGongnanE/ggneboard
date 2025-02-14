package com.ggne.ggneboard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Deprecated
@Getter
@NoArgsConstructor
@Table(name = "post_type")
// @Entity
public class PostType {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "post_type_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public PostType(String name) {
        this.name = name;
    }
}
