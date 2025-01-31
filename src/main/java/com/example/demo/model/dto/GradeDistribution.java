package com.example.demo.model.dto;
import lombok.Data;

@Data
public class GradeDistribution {
    private Integer departmentId;    // 部门ID
    private String grade;            // 绩效等级，例如 "A", "B", "C"
    private Integer count;           // 每个等级的分布数量
}
