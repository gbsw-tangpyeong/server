package com.tpc.groot.course.dto;

import com.nimbusds.jose.util.Pair;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseDto {
    private String title;
    private int totalDistance;
    private List<Pair<Float, Float>> latLngs;
}
