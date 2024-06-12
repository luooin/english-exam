package com.school.entity;

import java.util.Date;

public class Exam {
    private Integer examId;

    private String examName;

    private String examContent;

    private Integer examType;

    private String needTime;

    private String examAuthor;

    private Date createTime;

    private Date updateTime;

    private String examAnswer;

    private String examAnalysis;

    private Float examGrade;

    private String modelIds;

    private Integer examFlag;

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName == null ? null : examName.trim();
    }

    public String getExamContent() {
        return examContent;
    }

    public void setExamContent(String examContent) {
        this.examContent = examContent == null ? null : examContent.trim();
    }

    public Integer getExamType() {
        return examType;
    }

    public void setExamType(Integer examType) {
        this.examType = examType;
    }

    public String getNeedTime() {
        return needTime;
    }

    public void setNeedTime(String needTime) {
        this.needTime = needTime == null ? null : needTime.trim();
    }

    public String getExamAuthor() {
        return examAuthor;
    }

    public void setExamAuthor(String examAuthor) {
        this.examAuthor = examAuthor == null ? null : examAuthor.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getExamAnswer() {
        return examAnswer;
    }

    public void setExamAnswer(String examAnswer) {
        this.examAnswer = examAnswer == null ? null : examAnswer.trim();
    }

    public String getExamAnalysis() {
        return examAnalysis;
    }

    public void setExamAnalysis(String examAnalysis) {
        this.examAnalysis = examAnalysis == null ? null : examAnalysis.trim();
    }

    public Float getExamGrade() {
        return examGrade;
    }

    public void setExamGrade(Float examGrade) {
        this.examGrade = examGrade;
    }

    public String getModelIds() {
        return modelIds;
    }

    public void setModelIds(String modelIds) {
        this.modelIds = modelIds == null ? null : modelIds.trim();
    }

    public Integer getExamFlag() {
        return examFlag;
    }

    public void setExamFlag(Integer examFlag) {
        this.examFlag = examFlag;
    }
}