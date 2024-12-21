package com.tpc.groot.user.entity;

import com.tpc.groot.course.entity.Course;
import com.tpc.groot.status.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
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
    private ProfileImg img;
}