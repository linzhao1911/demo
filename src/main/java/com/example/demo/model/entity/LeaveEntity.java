package com.example.demo.model.entity;
import com.example.demo.model.vo.LeaveVO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class LeaveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer day;
    private Integer employeeId;
    private String reason;
    private String type;
    private String startDate;
    private String endDate;
    private String status;

    public LeaveVO toVO(){
        LeaveVO leaveVO = new LeaveVO();
        leaveVO.setReason(reason);
        leaveVO.setStartDate(startDate);
        leaveVO.setEndDate(endDate);
        leaveVO.setEmployeeID(employeeId);
        leaveVO.setDay(day);
        leaveVO.setType(type);
        leaveVO.setStatus(status);
        return leaveVO;
    }
}
