package com.example.demo.mapper;

import com.example.demo.model.entity.DepartmentEntity;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface DepartmentMapper {

    // 查询所有部门
    @Select("SELECT * FROM department")
    @Results({@Result(property = "departmentId", column = "department_id"),
            @Result(property = "departmentName", column = "department_name")})
    List<DepartmentEntity> getAllDepartments();

    // 根据ID查询部门
    @Select("SELECT * FROM department WHERE department_id = #{departmentId}")
    @Results({@Result(property = "departmentId", column = "department_id"),
            @Result(property = "departmentName", column = "department_name")})
    DepartmentEntity getDepartmentById(@Param("departmentId") Integer departmentId);

    // 插入部门
    @Insert("INSERT INTO department (department_name) VALUES (#{departmentName})")
    @Options(useGeneratedKeys = true, keyProperty = "departmentId")
    int insertDepartment(DepartmentEntity department);

    // 更新部门信息
    @Update("UPDATE department SET department_name = #{departmentName} WHERE department_id = #{departmentId}")
    int updateDepartment(DepartmentEntity department);

    // 删除部门
    @Delete("DELETE FROM department WHERE department_id = #{departmentId}")
    int deleteDepartment(@Param("departmentId") Integer departmentId);
}