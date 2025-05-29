package com.example.pointsystem;

import com.example.pointsystem.domain.Point;
import com.example.pointsystem.domain.PointTransaction;
import com.example.pointsystem.dto.PointRequestDto;
import com.example.pointsystem.repository.PointRepository;
import com.example.pointsystem.repository.PointTransactionRepository;
import com.example.pointsystem.service.PointService;
import com.example.pointsystem.util.TransactionIdGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;  // 자바일 때

import java.util.List;

@SpringBootTest
public class PointServiceIntegrationTest {
    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private PointTransactionRepository pointTransactionRepository;

    @Autowired
    private PointService pointService;

    @Autowired
    private TransactionIdGenerator transactionIdGenerator;

    @Test
    void earnPoints(){
        // given
        PointRequestDto request = new PointRequestDto();
        request.setUserId(100L);
        request.setAmount(500);

        // when
        pointService.earnPoints(request);

        // then
        List<Point> points = pointRepository.findByUserId(100L);
        List<PointTransaction> transactions = pointTransactionRepository.findByUserId(100L);

        assertThat(points).isNotEmpty();
        assertThat(transactions).isNotEmpty();
        assertThat(transactions.get(0).getChangeAmount()).isEqualTo(500);

    }


}
