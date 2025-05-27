
package com.example.pointsystem.repository;

import com.example.pointsystem.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {
    // TODO: 필요한 쿼리 작성
}
