package com.tpc.groot.status;

import com.tpc.groot.course.entity.Course;
import com.tpc.groot.user.entity.CustomUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalDistance;

    @OneToMany
    @JoinColumn(name="courseId", referencedColumnName = "id")
    private List<Course> courses;
}