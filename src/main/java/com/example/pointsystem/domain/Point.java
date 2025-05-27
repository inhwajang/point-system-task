
package com.example.pointsystem.domain;

import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private PointType type;
}
