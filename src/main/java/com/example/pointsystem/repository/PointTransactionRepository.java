
package com.example.pointsystem.repository;

import com.example.pointsystem.domain.PointTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointTransactionRepository extends JpaRepository<PointTransaction, Long> {
    // TODO: 필요한 쿼리 작성

    List<PointTransaction> findByUserId(Long userId);

}
