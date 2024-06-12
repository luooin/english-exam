package com.school.entity;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Example {
    //试题map
    private Map<Integer, ModelVo> modelMap = new ConcurrentHashMap<>();

    //答案map
    private Map<Integer, String> answerMap = new ConcurrentHashMap<>();

    //当前试题序号
    private Integer num;

    private Long startTime;//考试开始时间

    private Long endTime;//考试结束时间

    //试题的id
    private Set<Integer> idsSet = new HashSet<>();
    //试题的内容
    private Set<ModelVo> modelVoSet = new HashSet<>();

    public Map<Integer, ModelVo> getModelMap() {
        return modelMap;
    }

    public void setModelMap(Map<Integer, ModelVo> modelMap) {
        this.modelMap = modelMap;
    }

    public Map<Integer, String> getAnswerMap() {
        return answerMap;
    }

    public void setAnswerMap(Map<Integer, String> answerMap) {
        this.answerMap = answerMap;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Set<Integer> getIdsSet() {
        return idsSet;
    }

    public void setIdsSet(Set<Integer> idsSet) {
        this.idsSet = idsSet;
    }

    public Set<ModelVo> getModelVoSet() {
        return modelVoSet;
    }

    public void setModelVoSet(Set<ModelVo> modelVoSet) {
        this.modelVoSet = modelVoSet;
    }
}
