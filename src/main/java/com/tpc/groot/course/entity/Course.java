package com.tpc.groot.course.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 코스 이름
    private String name;

    // 커브
    @OneToMany
    @JoinColumn(name = "polyline", referencedColumnName = "id")
    private List<Polyline> polyline;

    // 코스 총 거리
    private int totalDistance;

    // 현재 진행 거리
    private int ranDistance;
}
