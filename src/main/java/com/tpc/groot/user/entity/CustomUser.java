package com.tpc.groot.user.entity;

import com.tpc.groot.course.entity.Course;
import com.tpc.groot.status.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
public class CustomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    private String phone;

    private String address;
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "statusId", referencedColumnName = "id")
    private Status status;

    @OneToOne
    @JoinColumn(name = "imgId", referencedColumnName = "id")
    private ProfileImg img;

    public CustomUser() {}
    public CustomUser(String username, String password, String email, String phone, String address, LocalDateTime createdAt) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.createdAt = createdAt;
    }
}