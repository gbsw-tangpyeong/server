package com.tpc.groot.course.service;

import com.tpc.groot.course.entity.Course;
import com.tpc.groot.course.repository.CourseRepository;
import com.tpc.groot.status.Status;
import com.tpc.groot.status.StatusRepository;
import com.tpc.groot.user.repository.UserRepository;
import com.tpc.groot.user.entity.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CourseService {

    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final CourseRepository courseRepository;

    public Status UpdateTotalDistance(long courseId, int distance){
        Optional<Course> course = courseRepository.findById(courseId);
        CustomUser user = courseRepository.findByUser(course.get().getUser());
        Status status = statusRepository.findByUser(user);
        status.setTotalDistance(status.getTotalDistance() + distance);

        return statusRepository.save(status);
    }

    public Course UpdateRanDistance(long courseId, int distance){
        Optional<Course> course = courseRepository.findById(courseId);
        course.get().setRanDistance(course.get().getRanDistance() + distance);

        return courseRepository.save(course.get());
    }
}
