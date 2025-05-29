package com.example.pointsystem;

import com.example.pointsystem.domain.Point;
import com.example.pointsystem.domain.PointType;
import com.example.pointsystem.dto.PointRequestDto;
import com.example.pointsystem.repository.PointRepository;
import com.example.pointsystem.repository.PointTransactionRepository;
import com.example.pointsystem.service.PointService;
import com.example.pointsystem.util.TransactionIdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class PointServiceTest {

    @Mock
    private PointRepository pointRepository;

    @Mock
    private PointTransactionRepository pointTransactionRepository;

    @Mock
    private TransactionIdGenerator transactionIdGenerator;

    @InjectMocks
    private PointService pointService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void earnPoints_ValidRequest_SavesPointAndTransaction() {
        // given
        Long userId = 1L;
        int amount = 500;
        String transactionId = "txn-123";

        PointRequestDto request = new PointRequestDto();
        request.setUserId(userId);
        request.setAmount(amount);

        Point savedPoint = new Point();
        savedPoint.setId(100L);  // simulate DB-assigned ID

        when(transactionIdGenerator.generate()).thenReturn(transactionId);
        when(pointRepository.saveAndFlush(any(Point.class))).thenReturn(savedPoint);
        when(pointTransactionRepository.findByPointId(transactionId, userId)).thenReturn(false);

        // when
        pointService.earnPoints(request);

        // then
        verify(pointRepository, times(1)).saveAndFlush(any(Point.class));
        verify(pointTransactionRepository, times(1)).saveAndFlush(argThat(txn ->
                txn.getTransactionId().equals(transactionId)
                        && txn.getUserId().equals(userId)
                        && txn.getChangeAmount() == amount
                        && txn.getType() == PointType.EARNED
        ));
    }
}
