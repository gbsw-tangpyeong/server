package com.tpc.groot.course.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistanceDto {
    private int distance;

    @NotNull
    private Long courseId;
}
