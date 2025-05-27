
package com.example.pointsystem.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PointTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private int changeAmount;
    private String description;
    private LocalDateTime createdAt;
}
