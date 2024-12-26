package com.tpc.groot.course.controller;

import com.nimbusds.jose.util.Pair;
import com.tpc.groot.course.dto.CourseDto;
import com.tpc.groot.course.entity.Course;
import com.tpc.groot.course.entity.Polyline;
import com.tpc.groot.course.repository.CourseRepository;
import com.tpc.groot.course.service.CourseService;
import com.tpc.groot.course.dto.DistanceDto;
import com.tpc.groot.status.Status;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody CourseDto dto) {
        Course course = courseService.createCourse(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable Long courseId) {
        Course c = courseService.getCourse(courseId);
        if (c != null) {
            return ResponseEntity.ok(c);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
    }
}
