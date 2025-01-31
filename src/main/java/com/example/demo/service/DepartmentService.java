package com.example.demo.service;
import com.example.demo.model.dto.DepartmentDTO;
import com.example.demo.model.vo.DepartmentVO;
import java.util.List;

public interface DepartmentService {
    List<DepartmentVO> getAllDepartments(); // 获取所有部门
    DepartmentVO getDepartmentById(Integer id); // 根据 ID 获取部门
    int insertDepartment(DepartmentDTO department); // 插入新部门
    int updateDepartment(DepartmentDTO department); // 更新部门信息
    int deleteDepartment(Integer id); // 删除部门
}
