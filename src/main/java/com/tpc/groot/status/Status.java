package com.tpc.groot.status;

import com.tpc.groot.course.entity.Course;
import com.tpc.groot.user.entity.CustomUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
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

    public Status() {}
    public Status(CustomUser user, int totalDistance) {
        this.user = user;
        this.totalDistance = totalDistance;
    }
}