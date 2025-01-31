package com.example.demo.mapper;
import com.example.demo.model.entity.EmployeeEntity;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface EmployeeMapper {

    // 查询所有员工
    @Select("SELECT * FROM employee")
    @Results({@Result(property = "employeeName", column = "name"),
            @Result(property = "departmentId", column = "department_id")})
    List<EmployeeEntity> getAllEmployees();

    // 根据ID查询员工
    @Select("SELECT * FROM employee WHERE id = #{id}")
    EmployeeEntity getEmployeeById(@Param("id") Integer id);

    // 插入员工
    @Insert("INSERT INTO employee (name, salary, department_id,age) VALUES (#{employeeName}, #{salary}, #{departmentId},#{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertEmployee(EmployeeEntity employee);

    // 更新员工信息
    @Update("UPDATE employee SET name = #{employeeName}, salary = #{salary}, department_id = #{departmentId} WHERE id = #{id}")
    int updateEmployee(EmployeeEntity employee);

    // 删除员工
    @Delete("DELETE FROM employee WHERE id = #{id}")
    int deleteEmployee(@Param("id") Integer id);

    @SelectProvider(type = EmployeeSqlProvider.class, method = "getEmployeesByCondition")
    @Results({@Result(property = "employeeName", column = "name"),
            @Result(property = "departmentId", column = "department_id")})
    List<EmployeeEntity> getEmployeesByCondition(
            @Param("name") String name,
            @Param("age") Integer age,
            @Param("salary") Integer salary,
            @Param("id") Integer id
    );
}