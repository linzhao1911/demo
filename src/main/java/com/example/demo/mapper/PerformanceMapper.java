package com.example.demo.mapper;
import com.example.demo.model.dto.PerformanceStatisticDTO;
import com.example.demo.model.entity.PerformanceEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;

@Mapper
public interface PerformanceMapper {
    // 1. 新增员工绩效考核记录
    @Insert("INSERT INTO performance (employee_id, performance_time, score, grade, comment, create_time, update_time) " +
            "VALUES (#{employeeId}, #{performanceTime}, #{score}, #{grade}, #{comment}, NOW(), NOW())")
    void insertPerformanceRecord(PerformanceEntity performanceEntity);


    // 2. 查询绩效考核记录
    @Select("<script>"
            + "SELECT p.* "
            + "FROM performance p "
            + "WHERE 1 = 1 "
            + "<if test='employeeId!= null'>"
            + "AND p.employee_id = #{employeeId} "
            + "</if>"
            + "<if test='departmentId!= null'>"
            + "AND p.employee_id IN ( "
            + "SELECT e.employee_id FROM employee e WHERE e.department_id = #{departmentId} "
            + ")"
            + "</if>"
            + "</script>")
    List<PerformanceEntity> getPerformanceRecords(@Param("employeeId") Integer employeeId, @Param("departmentId") Integer departmentId);



    // 3. 统计每个部门的平均得分和查询每个部门的绩效分布
    @Select("SELECT "
            + "    d.department_name AS departmentName, "
            + "    AVG(p.score) AS averageScore, "
            + "    JSON_OBJECT( "
            + "        'A', SUM(CASE WHEN p.grade = 'A' THEN 1 ELSE 0 END), "
            + "        'B', SUM(CASE WHEN p.grade = 'B' THEN 1 ELSE 0 END), "
            + "        'C', SUM(CASE WHEN p.grade = 'C' THEN 1 ELSE 0 END), "
            + "        'D', SUM(CASE WHEN p.grade = 'D' THEN 1 ELSE 0 END) "
            + "    ) AS gradeDistribution "
            + "FROM "
            + "    department d "
            + "JOIN "
            + "    employee e ON d.department_id = e.department_id "
            + "JOIN "
            + "    performance p ON e.employee_id = p.employee_id "
            + "GROUP BY "
            + "    d.department_id, d.department_name")
        List<PerformanceStatisticDTO> getDepartmentPerformanceSummary();

    void batchInsert(@Param("list") List<PerformanceEntity> entities);
}