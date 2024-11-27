package com.tpc.groot.course.controller;

import com.tpc.groot.course.service.CourseService;
import com.tpc.groot.course.dto.DistanceDto;
import com.tpc.groot.status.Status;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/map")
@RequiredArgsConstructor
@Controller
public class CourseController {
    private final CourseService mapService;

    @PostMapping("/distance")
    public ResponseEntity<Status> distance(@RequestBody @Valid DistanceDto dto) {
        Status status = mapService.updateTotalDistance(
                dto.getUsername(),
                dto.getDistance()
        ); //TotalDistance

        return ResponseEntity.ok(status);
    }
}
