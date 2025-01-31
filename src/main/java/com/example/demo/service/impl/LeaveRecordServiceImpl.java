package com.example.demo.service.impl;
import com.example.demo.enums.LeaveEnum;
import com.example.demo.mapper.LeaveRecordMapper;
import com.example.demo.model.dto.LeaveDTO;
import com.example.demo.model.entity.LeaveEntity;
import com.example.demo.model.vo.LeaveVO;
import com.example.demo.service.LeaveRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveRecordServiceImpl implements LeaveRecordService {

    @Autowired
    private  LeaveRecordMapper leaveRecordMapper;

    @Override
    public boolean applyLeaveRecord(LeaveDTO leaveDTO) {
        LeaveEntity leaveRecord = new LeaveEntity();
        leaveRecord.setDay(leaveDTO.getDay());
        leaveRecord.setEmployeeId(leaveDTO.getEmployeeId());
        leaveRecord.setType(leaveDTO.getType());
        leaveRecord.setStartDate(leaveDTO.getStartDate());
        leaveRecord.setEndDate(leaveDTO.getEndDate());
        leaveRecord.setStatus(LeaveEnum.PENDING.name());
        leaveRecord.setReason(leaveDTO.getReason());
        int l = leaveRecordMapper.addLeaveRecord(leaveRecord);
        return l > 0;
    }

    @Autowired
    public LeaveRecordServiceImpl(LeaveRecordMapper leaveRecordMapper) {
        this.leaveRecordMapper = leaveRecordMapper;
    }

    @Override
    public List<LeaveVO> getLeaveRecordsByEmployeeId(Integer employeeId) {
        List<LeaveEntity> leaveRecords = leaveRecordMapper.findByEmployeeId(employeeId);
        List<LeaveVO> leaveVOS = new ArrayList<>();
        for(LeaveEntity leaveRecord : leaveRecords){
            String chineseName = LeaveEnum.getChineseName(leaveRecord.getStatus());
            leaveRecord.setStatus(chineseName);
            LeaveVO leaveVO = new LeaveVO();
            leaveVO.setDay(leaveRecord.getDay());
            leaveVO.setEmployeeID(leaveRecord.getEmployeeId());
            leaveVO.setType(leaveRecord.getType());
            leaveVO.setReason(leaveRecord.getReason());
            leaveVO.setStartDate(leaveRecord.getStartDate());
            leaveVO.setEndDate(leaveRecord.getEndDate());
            leaveVOS.add(leaveVO);
        }
        return leaveVOS;
    }

    @Override
    public boolean approveLeaveRecord(Integer id, String status) {
        int rowsAffected = leaveRecordMapper.approveLeaveRecord(id, status);
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteLeaveRecord(Integer id) {
        int rowsAffected = leaveRecordMapper.deleteLeaveRecord(id);
        return rowsAffected > 0;
    }
}
