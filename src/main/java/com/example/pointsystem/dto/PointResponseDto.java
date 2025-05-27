
package com.example.pointsystem.dto;

public class PointResponseDto {
    private Long userId;
    private int currentBalance;
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    @Override
    public String toString() {
        return "PointResponseDto{" +
                "userId=" + userId +
                ", currentBalance=" + currentBalance +
                '}';
    }
}
