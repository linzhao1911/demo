package com.example.demo.model.dto;

import lombok.Data;

@Data
public class PerformanceStatisticDTO {
    private String departmentName;
    private String averageScore;
    private String gradeDistribution;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(String averageScore) {
        this.averageScore = averageScore;
    }

    public String getGradeDistribution() {
        return gradeDistribution;
    }

    public void setGradeDistribution(String gradeDistribution) {
        this.gradeDistribution = gradeDistribution;
    }
}
