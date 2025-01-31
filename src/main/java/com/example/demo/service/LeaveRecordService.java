package com.example.demo.service;
import com.example.demo.model.dto.LeaveDTO;
import com.example.demo.model.vo.LeaveVO;
import java.util.List;

public interface LeaveRecordService {
    boolean applyLeaveRecord(LeaveDTO leaveDTO);
    List<LeaveVO> getLeaveRecordsByEmployeeId(Integer employeeId);
    boolean approveLeaveRecord(Integer id, String status);
    boolean deleteLeaveRecord(Integer id);
}
