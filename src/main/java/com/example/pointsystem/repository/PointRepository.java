
package com.example.pointsystem.repository;

import com.example.pointsystem.domain.Point;
import com.example.pointsystem.dto.PointRequestDto;
import com.example.pointsystem.dto.PointResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Long> {
    // TODO: 필요한 쿼리 작성

    // findAll


    // 사용자 유저로 포인트 리스트 찾기
    List<Point> findByUserId(Long userId);

    @Query("select sum(amount) from Point where userId : userId" )
    int findAllUserId(Long userId);

    List<Point> findAll();


}
