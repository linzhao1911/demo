package com.example.demo.controller;
import com.example.demo.model.dto.PerformanceDTO;
import com.example.demo.model.entity.PerformanceEntity;
import com.example.demo.model.vo.PerformanceStatisticVO;
import com.example.demo.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/performance")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    // 1. 新增员工绩效考核记录
    @PostMapping("/add")
    public String addPerformanceRecord(@RequestBody PerformanceDTO performanceDTO) {
        performanceService.addPerformanceRecord(performanceDTO);
        return "Performance record added successfully.";
    }

    // 2. 查询绩效考核记录（根据员工 ID 或部门 ID）
    @GetMapping("/records")
    public List<PerformanceEntity> getPerformanceRecords(
            @RequestParam(required = false) Integer employeeId,
            @RequestParam(required = false) Integer departmentId) {
        return performanceService.getPerformanceRecords(employeeId, departmentId);
    }

    // 3. 统计每个部门的平均得分和绩效分布
    @GetMapping("/department/summary")
    public List<PerformanceStatisticVO> getDepartmentPerformanceSummary() {
        return performanceService.getDepartmentPerformanceSummary();
    }
}