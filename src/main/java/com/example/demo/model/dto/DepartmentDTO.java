package com.example.demo.model.dto;
import com.example.demo.model.entity.DepartmentEntity;
import lombok.Data;
@Data
public class DepartmentDTO {
    private Integer departmentId;
    private String departmentName;

    public DepartmentEntity toEntity(){
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setDepartmentId(departmentId);
        departmentEntity.setDepartmentName(departmentName);
        return departmentEntity;
    }
}
