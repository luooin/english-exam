package com.school.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.dao.GradeMapper;
import com.school.entity.Grade;
import com.school.entity.User;
import com.school.service.GradeService;
import com.school.utils.RoleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public Integer insertGrade(Grade grade) {
        return gradeMapper.insert(grade);
    }

    @Override
    public List<Grade> getGradeByUserName(String username) {
        return gradeMapper.getGradeByUserName(username);
    }

    @Override
    public Grade getGradeById(Integer gradeId) {
        return gradeMapper.selectByPrimaryKey(gradeId);
    }

    @Override
    public Grade getGradeByExamId(String username, Integer examId) {
        return gradeMapper.getGradeByExamId(username, examId);
    }

    @Override
    public List<Grade> getAllGrade() {
        return gradeMapper.getAllGrade();
    }

    @Override
    public Integer updateGradeById(Grade grade) {
        return gradeMapper.updateByPrimaryKey(grade);
    }

    @Override
    public List<Grade> getGradeByTitle(String title) {
        if (title == null || title.equals("")) {
            return gradeMapper.getAllGrade();
        }
        return gradeMapper.getGradeByTitle(title);
    }

    @Override
    public PageInfo<Grade> getGradeByUserNameAndTitle(Integer currentPage, Integer pageSize, String username, String title) {
        //设置分页信息保存到threadLocal中
        PageHelper.startPage(currentPage, pageSize);//一定要放在查询之前
        if (title == null || title.equals("")) {
            //所有条件为空
            List<Grade> list = gradeMapper.getGradeByUserName(username);
            PageInfo<Grade> pageInfo = new PageInfo<>(list);
            return pageInfo;
        }
        List<Grade> list = gradeMapper.getGradeByUserNameAndTitle(username, title);
        PageInfo<Grade> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<Grade> getAllGradeByCondition(Integer currentPage, Integer pageSize, String condition, String info) {
        //设置分页信息保存到threadLocal中
        PageHelper.startPage(currentPage, pageSize);//一定要放在查询之前
        List<Grade> list = new ArrayList<>();
        if ((info == null || info.equals("")) && (condition == null || condition.equals(""))) {
            //所有条件为空
            list = gradeMapper.getAllGrade();
            PageInfo<Grade> pageInfo = new PageInfo<>(list);
            return pageInfo;
        }

        if ((info == null || info.equals("")) && (condition != null || !condition.equals(""))) {
            //查询条件为空
            list = gradeMapper.getAllGrade();
            PageInfo<Grade> pageInfo = new PageInfo<>(list);
            return pageInfo;
        }

        //紧跟着的第一个select方法会被分页，userMapper会被PageInterceptor截拦,
        // 截拦器会从threadLocal中取出分页信息，把分页信息加到sql语句中，实现了分页查询
        if (condition.equals("0")) {
            //试卷名称
            list = gradeMapper.getGradeListByTitle(info);
        } else if (condition.equals("1")) {
            //班级名称
            list = gradeMapper.getGradeListByGroupName(info);
        } else if (condition.equals("2")) {
            //学号
            list = gradeMapper.getGradeListByUserName(info);
        } else if (condition.equals("3")) {
            //姓名
            list = gradeMapper.getGradeListByName(info);
        }
        PageInfo<Grade> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
