package com.school.entity;

import java.util.ArrayList;
import java.util.List;

public class ChartsVo {
    private Exam exam;
    List<Grade> list = new ArrayList<>();
    private Float average;//平均分

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public List<Grade> getList() {
        return list;
    }

    public void setList(List<Grade> list) {
        this.list = list;
    }

    public Float getAverage() {
        return average;
    }

    public void setAverage(Float average) {
        this.average = average;
    }

    @Override
    public String toString() {
        return "ChartsVo{" +
                "exam=" + exam +
                ", list=" + list +
                ", average=" + average +
                '}';
    }
}
