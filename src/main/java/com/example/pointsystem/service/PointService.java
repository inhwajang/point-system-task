
package com.example.pointsystem.service;

import com.example.pointsystem.domain.Point;
import com.example.pointsystem.domain.PointTransaction;
import com.example.pointsystem.domain.PointType;
import com.example.pointsystem.dto.PointRequestDto;
import com.example.pointsystem.dto.PointResponseDto;
import com.example.pointsystem.repository.PointRepository;
import com.example.pointsystem.repository.PointTransactionRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointService {
    // TODO: 포인트 적립, 사용, 조회 로직 구현
    private final PointRepository pointRepository;
    private final PointTransactionRepository pointTransactionRepository;

    private static final Logger log = LoggerFactory.getLogger(PointService.class);

    public PointService(PointRepository pointRepository, PointTransactionRepository pointTransactionRepository
            ) {
        this.pointRepository = pointRepository;
        this.pointTransactionRepository = pointTransactionRepository;
    }

    // 포인트조회
    @Transactional
    public List<Point> findPoints(Long userId){
        // 사용자별 포인트조회
       return pointRepository.findByUserId(userId);
    }

    @Transactional
    public List<Point> findAll(){
        return  pointRepository.findAll();
    }

    @Transactional
    public void earnPoints(PointRequestDto request){
        Long userId = request.getUserId();
        int amount = request.getAmount();
        if(userId != null && amount > 0){
            Point point = new Point();
            point.setAmount(amount);
            point.setUserId(userId);

            pointRepository.save(point);

            PointTransaction pointTransaction = new PointTransaction();
            pointTransaction.setChangeAmount(amount);
            pointTransaction.setUserId(userId);
            pointTransaction.setType(PointType.EARNED);
            pointTransactionRepository.save(pointTransaction);
        }else{
            log.error("Invalid point request: userId={}, amount={}", userId, amount);
        }
    }




}
