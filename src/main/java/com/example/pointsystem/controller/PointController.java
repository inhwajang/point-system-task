
package com.example.pointsystem.controller;

import com.example.pointsystem.domain.Point;
import com.example.pointsystem.dto.PointRequestDto;
import com.example.pointsystem.dto.PointResponseDto;
import com.example.pointsystem.service.PointService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/points")
public class PointController {
    // TODO: 서비스 주입 및 API 작성

    private final PointService pointService;

    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    // 사용자 별 포인트 내역 조회
    @PostMapping("/findPoints")
    public List<Point> findPoints(Long userId){
        List<Point> list = new ArrayList<>();
        list = pointService.findPoints(userId);
        System.out.print("list : " + list);
        return list;
    }

    @PostMapping("/findAll")
    public List<Point> findAllPoints(){
        List<Point> list = new ArrayList<>();
        list = pointService.findAll();
        System.out.print("list : " + list);
        return list;
    }


}
