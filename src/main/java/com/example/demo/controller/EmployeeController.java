package com.example.demo.controller;
import com.example.demo.model.dto.EmployeeDTO;
import com.example.demo.model.vo.EmployeeVO;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // 获取所有员工
    @GetMapping
    public List<EmployeeVO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // 根据ID获取员工
    @GetMapping("/{id}")
    public EmployeeVO getEmployeeById(@PathVariable Integer id) {

        return employeeService.getEmployeeById(id);
    }

    // 新增员工
    @PostMapping
    public String addEmployee(@RequestBody EmployeeDTO employee) {
        int result = employeeService.insertEmployee(employee);
        return result > 0 ? "Employee added successfully" : "Failed to add employee";
    }

    // 更新员工信息
    @PutMapping
    public String updateEmployee(@RequestBody EmployeeDTO employee) {
        int result = employeeService.updateEmployee(employee);
        return result > 0 ? "Employee updated successfully" : "Failed to update employee";
    }

    // 删除员工
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        int result = employeeService.deleteEmployee(id);
        return result > 0 ? "Employee deleted successfully" : "Failed to delete employee";
    }

    // 根据条件查询员工要求按照 姓名（模糊查询），年龄，工资（大于某个值），ID
    @GetMapping("/bycondition")
    public List<EmployeeVO> getEmployeesByCondition(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Integer salary,
            @RequestParam(required = false) Integer id
    ){
        return employeeService.getEmployeesByCondition(name, age, salary, id);
    }
}