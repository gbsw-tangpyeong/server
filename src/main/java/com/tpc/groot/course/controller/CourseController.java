package com.tpc.groot.course.controller;

import com.tpc.groot.course.entity.Course;
import com.tpc.groot.course.repository.CourseRepository;
import com.tpc.groot.course.service.CourseService;
import com.tpc.groot.course.dto.DistanceDto;
import com.tpc.groot.status.Status;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/map")
@RequiredArgsConstructor
@RestController
public class CourseController {

    private final CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<Course> getAllCourse(){
        return courseRepository.findAll();
    }

    @PostMapping("/distance")
    public ResponseEntity<Status> distance(@RequestBody @Valid DistanceDto dto) {
        Status status = courseService.UpdateTotalDistance(
                dto.getCourseId(),
                dto.getDistance()
        ); //TotalDistance

        Course course = courseService.UpdateRanDistance(
                dto.getCourseId(),
                dto.getDistance()
        );

        return ResponseEntity.ok(status);
    }
}
