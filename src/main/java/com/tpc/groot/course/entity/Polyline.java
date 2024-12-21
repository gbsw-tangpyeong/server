package com.tpc.groot.course.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Polyline {
    @Id
    private long id;

    private double lat;

    private double lng;

    private int seq;
}
