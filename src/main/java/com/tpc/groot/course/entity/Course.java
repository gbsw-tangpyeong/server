package com.tpc.groot.course.entity;

import com.tpc.groot.status.Status;
import com.tpc.groot.user.entity.CustomUser;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 코스 이름
    private String title;

    // 커브
    @Setter
    @OneToMany(mappedBy = "course")
    private List<Polyline> polylines;

    // 코스 총 거리
    private int totalDistance;

    // 현재 진행 거리
    @Setter
    private int ranDistance;

    @ManyToOne
    @JoinColumn(name = "statusId", referencedColumnName = "id") // Status와의 관계 설정
    private Status status;

    public Course(String title, int totalDistance, Status status) {
        this.title = title;
        this.totalDistance = totalDistance;
        this.ranDistance = 0;
    }
}