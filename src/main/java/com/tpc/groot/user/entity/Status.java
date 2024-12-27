package com.tpc.groot.user.entity;

import com.tpc.groot.course.entity.Course;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private int totalDistance;

    @OneToMany(mappedBy = "status")
    private List<Course> courses;

    @OneToOne
    private CustomUser user;

    public Status(CustomUser user, int totalDistance) {
        this.user = user;
        this.totalDistance = totalDistance;
    }
}