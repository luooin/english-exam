package com.school.utils;

public class RoleUtil {
    public enum RoleType{
        STUDENT(0,"学生"),
        TEACHER(1,"教师"),
        ADMIN(100,"管理员")
        ;
        private Integer value;
        private String desc;

        RoleType() {
        }

        RoleType(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
    public static Integer getRole(String info){
        if (info.contains("教师")||info.contains("教")||info.contains("师")){
            return RoleType.TEACHER.getValue();
        }
        if (info.contains("学生")||info.contains("学")||info.contains("生")){
            return RoleType.STUDENT.getValue();
        }
        return null;
    }

    public static String getRoleDesc(Integer value){
        if (value == 0){
            return RoleType.STUDENT.getDesc();
        }else if (value == 1){
            return RoleType.TEACHER.getDesc();
        }
        return RoleType.STUDENT.getDesc();
    }
}
