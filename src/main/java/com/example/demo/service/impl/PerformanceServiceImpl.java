package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.mapper.PerformanceMapper;
import com.example.demo.model.dto.PerformanceDTO;
import com.example.demo.model.dto.PerformanceStatisticDTO;
import com.example.demo.model.entity.PerformanceEntity;
import com.example.demo.model.vo.PerformanceStatisticVO;
import com.example.demo.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class PerformanceServiceImpl implements PerformanceService {

    @Autowired
    private PerformanceMapper performanceMapper;

    // 1. 新增员工绩效考核记录
    @Override
    public void addPerformanceRecord(PerformanceDTO performanceDTO) {
        PerformanceEntity performanceEntity = new PerformanceEntity();
        performanceEntity.setEmployeeId(performanceDTO.getEmployeeId());
        performanceEntity.setScore(performanceDTO.getScore().intValue());
        performanceEntity.setGrade(performanceDTO.getGrade());
        performanceEntity.setPerformanceTime(performanceDTO.getPerformanceTime());  // 设置考核期
        performanceEntity.setCreateTime(LocalDateTime.now().toString());  // 使用字符串表示时间
        performanceEntity.setUpdateTime(LocalDateTime.now().toString());  // 使用字符串表示时间
        performanceEntity.setComment(performanceDTO.getComments());
        performanceMapper.insertPerformanceRecord(performanceEntity);
    }

    // 2. 查询绩效考核记录
    @Override
    public List<PerformanceEntity> getPerformanceRecords(Integer employeeId, Integer departmentId) {
        List<PerformanceEntity> records = performanceMapper.getPerformanceRecords(employeeId, departmentId);
        // 示例：打印日志
        records.forEach(record -> {
            // 假设 record.getGrade() 返回的是 String 类型的绩效等级
            System.out.println("Grade: " + record.getGrade());  // 直接打印 grade 字符串
        });
        return records;
    }

    // 3. 统计每个部门的平均得分和绩效分布
    @Override
    public List<PerformanceStatisticVO> getDepartmentPerformanceSummary() {
        List<PerformanceStatisticDTO> performanceStatisticDTOS = performanceMapper.getDepartmentPerformanceSummary();
        List<PerformanceStatisticVO> performanceStatisticVOList = new ArrayList<>();
        //BeanUtils.copyProperties(performanceStatisticDTOS, performanceStatisticVO);
        performanceStatisticDTOS.forEach(performanceStatisticDTO -> {
            PerformanceStatisticVO performanceStatisticVO = new PerformanceStatisticVO();
            performanceStatisticVO.setDepartmentName(performanceStatisticDTO.getDepartmentName());
            performanceStatisticVO.setAverageScore(performanceStatisticDTO.getAverageScore());
            performanceStatisticVO.setGradeDistribution(performanceStatisticDTO.getGradeDistribution());
            performanceStatisticVOList.add(performanceStatisticVO);
        });
        return performanceStatisticVOList;
    }

    // 添加批量处理能力
    @Transactional
    public void batchAddPerformance(List<PerformanceDTO> performanceDTOs) {
        List<PerformanceEntity> entities = new ArrayList<>();
        for (PerformanceDTO dto : performanceDTOs) {
            entities.add(convertToEntity(dto));
        }
        performanceMapper.batchInsert(entities);
    }
    
    // 修改分页查询方法的返回类型
    public PageInfo<PerformanceEntity> getPerformancesByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PerformanceEntity> records = performanceMapper.getPerformanceRecords(null, null);
        return new PageInfo<>(records);
    }

    private PerformanceEntity convertToEntity(PerformanceDTO dto) {
        PerformanceEntity entity = new PerformanceEntity();
        entity.setEmployeeId(dto.getEmployeeId());
        entity.setScore(dto.getScore().intValue());
        entity.setGrade(dto.getGrade());
        entity.setPerformanceTime(dto.getPerformanceTime());
        entity.setCreateTime(LocalDateTime.now().toString());
        entity.setUpdateTime(LocalDateTime.now().toString());
        entity.setComment(dto.getComments());
        return entity;
    }
}

