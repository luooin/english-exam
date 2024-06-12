package com.school.utils;

public class DifficultyUtil {

    enum Difficulty{
        EASY(0,"简单"),
        ORDINARY(1,"一般"),
        HARD(2,"困难")
        ;

        private Integer value;
        private String desc;

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

        Difficulty() {
        }

        Difficulty(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    }

    public static String getDifficulty(Integer type){
        if (type.equals(Difficulty.EASY.getValue())){
            return Difficulty.EASY.getDesc();
        }
        if (type.equals(Difficulty.ORDINARY.getValue())){
            return Difficulty.ORDINARY.getDesc();
        }
        if (type.equals(Difficulty.HARD.getValue())){
            return Difficulty.HARD.getDesc();
        }
        //默认简单
        return Difficulty.EASY.getDesc();
    }
}
