package com.example.demo.model.dto;
import lombok.Data;

@Data
public class PerformanceDTO {
    private Integer employeeId;
    private Integer score;
    private String grade;
    private String comments;
    private String performanceTime;// 考核期，作为String类型
}
