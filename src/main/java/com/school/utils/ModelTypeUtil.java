package com.school.utils;

public class ModelTypeUtil {
    public enum Type{
        BLANK(1,"选词填空"),
        PARAGRAPH(2,"段落匹配"),
        READING(3,"仔细阅读"),
        TRANSLATE(4,"翻译"),
        WRITING(5,"写作")
        ;

        private Integer value;
        private String desc;

        Type() {
        }

        Type(Integer value, String desc) {
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
}
