package com.school.service;

import com.github.pagehelper.PageInfo;
import com.school.entity.Grade;

import java.util.List;

public interface GradeService {

    //插入记录
    Integer insertGrade(Grade grade);

    //获取用户的考试记录
    List<Grade> getGradeByUserName(String username);

    //根据id获取grade
    Grade getGradeById(Integer gradeId);

    //判断用户是否已经考试
    Grade getGradeByExamId(String username,Integer examId);

    List<Grade> getAllGrade();

    //更新考试记录，修改考试成绩
    Integer updateGradeById(Grade grade);

    List<Grade> getGradeByTitle(String title);

    PageInfo<Grade> getGradeByUserNameAndTitle(Integer currentPage, Integer pageSize, String username, String title);

    //根据条件查询grade
    PageInfo<Grade> getAllGradeByCondition(Integer currentPage, Integer pageSize, String condition, String info);
}
