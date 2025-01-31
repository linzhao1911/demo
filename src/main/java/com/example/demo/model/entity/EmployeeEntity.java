package com.example.demo.model.entity;
import com.example.demo.model.vo.EmployeeVO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String employeeName;
    private Integer salary;
    private Integer departmentId;
    private Integer age;

    public EmployeeVO toVO(){
        EmployeeVO employeeVO = new EmployeeVO();
        employeeVO.setEmployeeId(id);
        employeeVO.setEmployeeName(employeeName);
        employeeVO.setSalary(salary);
        employeeVO.setDepartmentId(departmentId);
        employeeVO.setAge(age);
        return employeeVO;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id=" + id +
                ", employeeName='" + employeeName + '\'' +
                ", salary=" + salary +
                ", departmentId=" + departmentId +
                ", age=" + age +
                '}';
    }
}