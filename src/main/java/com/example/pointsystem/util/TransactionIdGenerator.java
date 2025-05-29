package com.example.pointsystem.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.UUID;

// 트랜잭션 Id 생성
@Component
public class TransactionIdGenerator {
    public  String generate(){
        return UUID.randomUUID().toString();
    }
}
