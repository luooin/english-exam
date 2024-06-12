package com.school.entity;


public class RecordVo {

    private Integer modelId;//试题id
    private String title;//标题
    private String type;//类型
    private String content;//内容
    private String modelOption;//选项
    private String modelAnswer;//试题答案
    private String analysis;//解析
    private String difficulty;//难度（默认简单0,1一般,2困难）
    private String author;//添加者
    private Float modelGrade;//model分值
    private Integer recordId;//记录id
    private String username;//用户名
    private String name;//姓名
    private Integer examId;//试卷id
    private String userAnswer;//用户答案

    public RecordVo() {

    }
    //构造考试记录
    public RecordVo(ModelVo modelVo,Record record){
        this.modelId = modelVo.getModel().getModelId();
        this.title = modelVo.getModel().getTitle();
        this.type = modelVo.getType();
        this.content = modelVo.getModel().getContent();
        this.modelOption = modelVo.getModel().getModelOption();
        this.modelAnswer = modelVo.getModel().getAnswer();
        this.analysis = modelVo.getModel().getAnalysis();
        this.difficulty =modelVo.getDifficulty();
        this.author = modelVo.getModel().getAuthor();
        this.modelGrade = modelVo.getModel().getGrade();
        this.recordId = record.getId();
        this.username = record.getUsername();
        this.name = record.getName();
        this.examId = record.getExamId();
        this.userAnswer = record.getAnswer();
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getModelOption() {
        return modelOption;
    }

    public void setModelOption(String modelOption) {
        this.modelOption = modelOption;
    }

    public String getModelAnswer() {
        return modelAnswer;
    }

    public void setModelAnswer(String modelAnswer) {
        this.modelAnswer = modelAnswer;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Float getModelGrade() {
        return modelGrade;
    }

    public void setModelGrade(Float modelGrade) {
        this.modelGrade = modelGrade;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}
