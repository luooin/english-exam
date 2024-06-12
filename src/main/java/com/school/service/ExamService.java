package com.school.service;

import com.github.pagehelper.PageInfo;
import com.school.entity.ChartsVo;
import com.school.entity.Exam;
import com.school.entity.Grade;
import com.school.entity.ModelVo;

import java.util.List;
import java.util.Set;

public interface ExamService {

    //新建试卷
    Integer insertExam(Exam exam);

    //整理model
    List<ModelVo>manageModel(String ids);

    //整理model
    List<ModelVo>manageModel(Set<ModelVo> modelVoSet);

    //获取所有exam
    List<Exam> getAllExam();

    //获取examById
    Exam getExamById(Integer exam_id);

    Integer deleteExam(Integer exam_id);
    //模糊查询
    List<Exam> getExamByCondition(String exam_name);

    //查询最近七天记录
    List<Exam> getExamRecently();

    //查询所有记录
    Long getExamCount();

    //更新Exam
    Integer updateExam(Exam exam);

    //分页查询
    PageInfo<Exam> getAllExamByCondition(Integer currentPage, Integer pageSize, String condition);

    //获取ChartsVo
    List<ChartsVo> getAllExamForCharts(Integer examId);

    //根据id获取grade显示到charts页面
    PageInfo<Grade> getGradesByCondition(Integer currentPage, Integer pageSize, String condition, String info, Integer examId);
}
