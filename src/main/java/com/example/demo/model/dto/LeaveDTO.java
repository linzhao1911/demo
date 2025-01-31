package com.example.demo.model.dto;
import lombok.Data;

@Data
public class LeaveDTO {
    private Integer day;
    private Integer employeeId;
    private String reason;
    private String type;
    private String startDate;
    private String endDate;


}
