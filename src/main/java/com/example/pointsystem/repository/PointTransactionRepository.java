
package com.example.pointsystem.repository;

import com.example.pointsystem.domain.PointTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PointTransactionRepository extends JpaRepository<PointTransaction, Long> {
    // TODO: 필요한 쿼리 작성

    List<PointTransaction> findByUserId(Long userId);


    @Query(" Select Exists (Select 1 from PointTransaction where transactionId = :transactionId and userId = :userId)")
    boolean findByPointId(@Param("transactionId") String transactionId, @Param("userId") Long userId);


}
