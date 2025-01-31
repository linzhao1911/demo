package com.example.demo.service;
import com.example.demo.model.dto.EmployeeDTO;
import com.example.demo.model.vo.EmployeeVO;
import java.util.List;

public interface EmployeeService {
    List<EmployeeVO> getAllEmployees();
    EmployeeVO getEmployeeById(Integer id);
    int insertEmployee(EmployeeDTO employee);
    int updateEmployee(EmployeeDTO employee);
    int deleteEmployee(Integer id);
    List<EmployeeVO> getEmployeesByCondition(String name, Integer age, Integer salary, Integer id);
}