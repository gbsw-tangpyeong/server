package com.tpc.groot.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

@Getter
@Setter
@Entity
public class CustomUser extends SecurityProperties.User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userid;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    private String profileImg;

    private String address;

    @Column(nullable = false)
    private String phone;
}
