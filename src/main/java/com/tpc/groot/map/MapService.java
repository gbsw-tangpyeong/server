package com.tpc.groot.map;

import com.tpc.groot.map.dto.TotalDistanceDto;
import com.tpc.groot.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MapService {
    private final UserRepository userRepository;

    public int updateDistance(TotalDistanceDto dto){
        int currentDistance = dto.getDistance();
        return currentDistance;
    }
}
