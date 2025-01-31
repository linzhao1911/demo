package com.example.demo.model.dto;
import lombok.Data;

@Data
public class DepartmentAverageScore {
    private Integer departmentId;    // 部门ID
    private String departmentName;   // 部门名称
    private Double averageScore;     // 平均分数
}
