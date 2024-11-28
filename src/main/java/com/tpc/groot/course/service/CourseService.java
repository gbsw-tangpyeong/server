package com.tpc.groot.course.service;

import com.tpc.groot.status.Status;
import com.tpc.groot.status.StatusRepository;
import com.tpc.groot.user.repository.UserRepository;
import com.tpc.groot.user.entity.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CourseService {

    private final UserRepository userRepository;
    private final StatusRepository statusRepository;

    public Status updateTotalDistance(String username, int distance){
        CustomUser user = userRepository.findByUsername(username);
        Status status = statusRepository.findByUser(user);
        status.setTotalDistance(status.getTotalDistance() + distance);

        return statusRepository.save(status);
    }
}
