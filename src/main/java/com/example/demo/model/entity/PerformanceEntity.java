package com.example.demo.model.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PerformanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer employeeId;
    private Integer score;
    private String grade;
    private String performanceTime; // 改为 LocalDateTime
    private String createTime;      // 改为 LocalDateTime
    private String updateTime;      // 改为 LocalDateTime
    private String comment;
}

