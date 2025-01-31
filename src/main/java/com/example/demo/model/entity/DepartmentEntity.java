package com.example.demo.model.entity;
import com.example.demo.model.vo.DepartmentVO;
import lombok.Data;

@Data
public class DepartmentEntity {
    private Integer departmentId;
    private String departmentName;


    public DepartmentVO toVO(DepartmentEntity departmentEntity) {
        DepartmentVO departmentVO = new DepartmentVO(departmentEntity);
        departmentVO.setDepartmentId(departmentId);
        departmentVO.setDepartmentName(departmentName);
        return departmentVO;
    }
}