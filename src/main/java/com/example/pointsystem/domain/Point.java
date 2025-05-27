
package com.example.pointsystem.domain;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;

import java.time.LocalDateTime;

@Entity

public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private int amount;
    private LocalDateTime expireAt;
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", userId=" + userId +
                ", amount=" + amount +
                ", expireAt=" + expireAt +
                ", createdAt=" + createdAt +
                ", type=" + type +
                '}';
    }

    @Enumerated(EnumType.STRING)
    private PointType type;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public PointType getType() {
        return type;
    }

    public void setType(PointType type) {
        this.type = type;
    }

}
