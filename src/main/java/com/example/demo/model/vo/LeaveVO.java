package com.example.demo.model.vo;
import lombok.Data;

@Data
public class LeaveVO {
    private Integer day;
    private Integer employeeID;
    private String reason;
    private String type;
    private String startDate;
    private String endDate;
    private Integer status;

    public void setStatus(String status) {
    }
}
