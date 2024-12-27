package com.tpc.groot.course.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Polyline {
    @Id
    private long id;

    private float lat;

    private float lng;

    private int seq;

    @ManyToOne
    @JoinColumn(name = "courseId", referencedColumnName = "id")
    private Course course;

    public Polyline(float lat, float lng, int seq, Course course) {
        this.lat = lat;
        this.lng = lng;
        this.seq = seq;
        this.course = course;
    }
}
