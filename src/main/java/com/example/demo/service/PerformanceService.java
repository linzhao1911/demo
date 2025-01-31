package com.example.demo.service;
import com.example.demo.model.dto.PerformanceDTO;
import com.example.demo.model.entity.PerformanceEntity;
import com.example.demo.model.vo.PerformanceStatisticVO;
import java.util.List;

public interface PerformanceService {
    void addPerformanceRecord(PerformanceDTO performanceDTO);
    List<PerformanceEntity> getPerformanceRecords(Integer employeeId, Integer departmentId);
    List<PerformanceStatisticVO> getDepartmentPerformanceSummary();
}


