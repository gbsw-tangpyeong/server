package com.tpc.groot.course.entity;

import com.tpc.groot.user.entity.CustomUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 코스 이름
    private String name;

    // 지도 api 데이터. 나중에 추가할 것.

    private int totalDistance;
    // 코스 총 거리

    private int ranDistance;
    // 현재 진행 거리

    @ManyToOne
    private CustomUser user;
}
