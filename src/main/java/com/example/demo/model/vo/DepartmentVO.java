package com.example.demo.model.vo;
import com.example.demo.model.entity.DepartmentEntity;
import lombok.Data;

@Data
public class DepartmentVO {
    private Integer departmentId;
    private String departmentName;

    public DepartmentVO(DepartmentEntity departmentEntity) {
        this.departmentId = departmentEntity.getDepartmentId();
        this.departmentName = departmentEntity.getDepartmentName();
    }
}
