package com.example.demo.model.vo;
import com.example.demo.model.entity.EmployeeEntity;
import lombok.Data;

import java.io.Serializable;

@Data

public class EmployeeVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer employeeId;
    private String employeeName;
    private Integer salary;
    private Integer departmentId;
    private Integer age;

    public EmployeeVO() {
    }

    public EmployeeVO(EmployeeEntity employeeEntity) {
        this.id = employeeEntity.getId();
        this.employeeId = employeeEntity.getId();
        this.employeeName = employeeEntity.getEmployeeName();
        this.salary = employeeEntity.getSalary();
        this.departmentId = employeeEntity.getDepartmentId();
        this.age = employeeEntity.getAge();
    }
}
