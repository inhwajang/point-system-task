
package com.example.pointsystem.repository;

import com.example.pointsystem.domain.Point;
import com.example.pointsystem.dto.PointRequestDto;
import com.example.pointsystem.dto.PointResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Long> {
    // TODO: 필요한 쿼리 작성

    // findAll


    PointResponseDto findByUserId(Long userId);



    List<Point> findAll();


}
