package com.example.demo.controller;

import com.example.demo.model.dto.DepartmentDTO;
import com.example.demo.model.vo.DepartmentVO;
import com.example.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

    @RestController
    @RequestMapping("/departments")
     public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // 获取所有部门
    @GetMapping
    public List<DepartmentVO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    // 根据ID获取部门
    @GetMapping("/{id}")
    public DepartmentVO getDepartmentById(@PathVariable Integer id) {
        return departmentService.getDepartmentById(id);
    }

    // 新增部门
    @PostMapping
    public String addDepartment(@RequestBody DepartmentDTO department) {
        int result = departmentService.insertDepartment(department);
        return result > 0 ? "Department added successfully" : "Failed to add department";
    }

    // 更新部门信息
    @PutMapping
    public String updateDepartment(@RequestBody DepartmentDTO department) {
        int result = departmentService.updateDepartment(department);
        return result > 0 ? "Department updated successfully" : "Failed to update department";
    }

    // 删除部门
    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Integer id) {
        int result = departmentService.deleteDepartment(id);
        return result > 0 ? "Department deleted successfully" : "Failed to delete department";
    }
}