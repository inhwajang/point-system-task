
package com.example.pointsystem.service;

import com.example.pointsystem.domain.Point;
import com.example.pointsystem.domain.PointTransaction;
import com.example.pointsystem.domain.PointType;
import com.example.pointsystem.dto.PointRequestDto;
import com.example.pointsystem.dto.PointResponseDto;
import com.example.pointsystem.repository.PointRepository;
import com.example.pointsystem.repository.PointTransactionRepository;
import com.example.pointsystem.util.TransactionIdGenerator;
import jakarta.transaction.RollbackException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointService {
    // TODO: 포인트 적립, 사용, 조회 로직 구현
    private final PointRepository pointRepository;
    private final PointTransactionRepository pointTransactionRepository;
    private final TransactionIdGenerator transactionIdGenerator;

    private static final Logger log = LoggerFactory.getLogger(PointService.class);

    public PointService(PointRepository pointRepository, PointTransactionRepository pointTransactionRepository, TransactionIdGenerator transactionIdGenerator
    ) {
        this.pointRepository = pointRepository;
        this.pointTransactionRepository = pointTransactionRepository;
        this.transactionIdGenerator = transactionIdGenerator;
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

            String transactionId = transactionIdGenerator.generate();

            Point point = new Point();
            point.setAmount(amount);
            point.setUserId(userId);

            pointRepository.saveAndFlush(point);

            boolean dupliYN = true;

            dupliYN = pointTransactionRepository.findByPointId(transactionId, userId);
            if(dupliYN){
                log.error("duplicated : userId={}, PointId={}", userId, point.getId());
                throw new IllegalStateException("Duplicated transaction");
            }else {
                PointTransaction pointTransaction = new PointTransaction();
                pointTransaction.setChangeAmount(amount);
                pointTransaction.setUserId(userId);
                pointTransaction.setType(PointType.EARNED);
                pointTransaction.setTransactionId(transactionId);
                pointTransactionRepository.saveAndFlush(pointTransaction);
            }

        }else{
            log.error("Invalid point request: userId={}, amount={}", userId, amount);
        }
    }

    @Transactional
    public void usedPoints(PointRequestDto request){
        Long userId = request.getUserId();

        if(userId != null){

            String transactionId = transactionIdGenerator.generate();

            Point point = new Point();
            point = pointRepository.findOnePoint(userId);
            point.setAmount(0);
            pointRepository.saveAndFlush(point);

            boolean dupliYN = true;

            dupliYN = pointTransactionRepository.findByPointId(transactionId, userId);
            if(dupliYN){
                log.error("duplicated : userId={}, PointId={}", userId, point.getId());
                throw new IllegalStateException("Duplicated transaction");
            }else {
                PointTransaction pointTransaction = new PointTransaction();
                pointTransaction.setChangeAmount(point.getAmount());
                pointTransaction.setUserId(userId);
                pointTransaction.setType(PointType.USED);
                pointTransaction.setTransactionId(transactionId);
                pointTransactionRepository.saveAndFlush(pointTransaction);
            }

        }else{
            log.error("Invalid point request: userId={}", userId);
        }
    }


}
