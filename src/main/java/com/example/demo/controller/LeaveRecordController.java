package com.example.demo.controller;
import com.example.demo.model.dto.LeaveDTO;
import com.example.demo.model.vo.LeaveVO;
import com.example.demo.service.LeaveRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/leaverecord")
public class LeaveRecordController {

    @Autowired
    private LeaveRecordService leaveRecordService;

    @PostMapping
    public String applyleave(@RequestBody LeaveDTO leaveRequest) {
        boolean b = leaveRecordService.applyLeaveRecord(leaveRequest);
        return b ? "apply leav successfully" : "Failed to apply leav";
    }

    @Autowired
    public LeaveRecordController(LeaveRecordService leaveRecordService) {
        this.leaveRecordService = leaveRecordService;
    }

    // 根据员工 ID 获取请假记录
    @GetMapping("/employee/{employeeId}")
    public List<LeaveVO> getLeaveRecordsByEmployeeId(@PathVariable("employeeId") Integer employeeId) {
        return leaveRecordService.getLeaveRecordsByEmployeeId(employeeId);
    }

    // 批准请假记录
    @PutMapping("/approve/{id}")
    public boolean approveLeaveRecord(@PathVariable("id") Integer id, @RequestParam String status) {
        return leaveRecordService.approveLeaveRecord(id, status);
    }

    // 删除请假记录
    @DeleteMapping("/delete/{id}")
    public boolean deleteLeaveRecord(@PathVariable("id") Integer id) {
        return leaveRecordService.deleteLeaveRecord(id);
    }
}
