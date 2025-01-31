package com.example.demo.enums;

public enum LeaveEnum {
    PENDING("待审批"),
    APPROVED("已通过"),
    REJECTED("已拒绝");
    private String desc;

    LeaveEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public static String getChineseName(String englishName) {
        for(LeaveEnum leaveEnum : LeaveEnum.values()){
            if(leaveEnum.name().equals(englishName)){
                return leaveEnum.getDesc();
            }
        }
        throw new IllegalArgumentException("当前无匹配的英文名");
    }
}