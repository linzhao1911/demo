package com.example.demo.model.dto;
import com.example.demo.model.entity.EmployeeEntity;
import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class EmployeeDTO {
    private Integer id;
    
    @NotNull(message = "员工姓名不能为空")
    @Size(min = 2, max = 20, message = "姓名长度必须在2-20之间")
    private String employeeName;
    
    @NotNull(message = "部门ID不能为空")
    private Integer departmentId;
    
    @Min(value = 0, message = "薪资不能为负数")
    private Double salary;
    
    @Min(value = 18, message = "年龄不能小于18岁")
    @Max(value = 65, message = "年龄不能大于65岁")
    private Integer age;

    public EmployeeEntity toEntity(){
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(id);
        employeeEntity.setEmployeeName(employeeName);
        employeeEntity.setSalary(salary.intValue());
        employeeEntity.setDepartmentId(departmentId);
        employeeEntity.setAge(age);
        return employeeEntity;
    }
}
