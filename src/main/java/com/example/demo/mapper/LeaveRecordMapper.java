package com.example.demo.mapper;
import com.example.demo.model.entity.LeaveEntity;
import org.apache.ibatis.annotations.*;
import java.util.List;


@Mapper
public interface LeaveRecordMapper {

    @Insert("INSERT INTO leave_record(reason,status,day,start_date,end_date,employee_id,type) " +
            "VALUES(#{reason},#{status},#{day},#{startDate},#{endDate},#{employeeId},#{type})")
    int addLeaveRecord(LeaveEntity leaveRecord);

    // 根据员工 ID 查询请假记录
    @Select("SELECT * FROM leave_record WHERE employee_id = #{employeeId}")
    @Results({@Result(property = "employeeId", column = "employee_id")})
    List<LeaveEntity> findByEmployeeId(@Param("employeeId") Integer employeeId);

    // 批准请假记录
    @Update("UPDATE leave_record SET status = #{status} WHERE id = #{id}")
    int approveLeaveRecord(@Param("id") Integer id, @Param("status") String status);

    // 删除请假记录
    @Delete("DELETE FROM leave_record WHERE id = #{id}")
    int deleteLeaveRecord(@Param("id") Integer id);
}