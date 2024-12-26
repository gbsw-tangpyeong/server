package com.tpc.groot.course.dto;

import com.tpc.groot.course.entity.LatLng;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseDto {
    private String title;
    private int totalDistance;
    private List<LatLng> latLngs;  // Pair 대신 커스텀 클래스 사용
}