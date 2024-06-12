package com.school.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.dao.ExamMapper;
import com.school.dao.GradeMapper;
import com.school.entity.ChartsVo;
import com.school.entity.Exam;
import com.school.entity.Grade;
import com.school.entity.ModelVo;
import com.school.service.ExamService;
import com.school.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private ModelService modelService;
    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public Integer insertExam(Exam exam) {
        return examMapper.insert(exam);
    }

    @Override
    public List<ModelVo> manageModel(String ids) {
        String[] split = ids.split(",");
        List<ModelVo> modelList = new ArrayList<>();
        //获取modelVo的Comparator
        Comparator<ModelVo> comparator = getModelVoComparator();
        for (String s:split){
            int model_id = Integer.parseInt(s);
            ModelVo m = modelService.getModelVo(model_id);
            modelList.add(m);
        }
        Collections.sort(modelList,comparator);
        return modelList;
    }

    @Override
    public List<ModelVo> manageModel(Set<ModelVo> modelVoSet) {
        //获取modelVo的Comparator
        Comparator<ModelVo> comparator = getModelVoComparator();
        List<ModelVo> modelList = new ArrayList<>(modelVoSet);

        Collections.sort(modelList,comparator);
        return modelList;
    }

    @Override
    public List<Exam> getAllExam() {
        return examMapper.getAllExam();
    }


    @Override
    public Exam getExamById(Integer exam_id) {
        return examMapper.selectByPrimaryKey(exam_id);
    }

    @Override
    public Integer deleteExam(Integer exam_id) {
        return examMapper.deleteByPrimaryKey(exam_id);
    }

    @Override
    public List<Exam> getExamByCondition(String exam_name) {
        return examMapper.getExamByCondition(exam_name);
    }

    @Override
    public List<Exam> getExamRecently() {
        List<Exam> examList = examMapper.getExamRecently();
        if (examList.size() >= 3){
            //取前三条数据
            return examList.subList(0, 3);
        }
        return examList;
    }

    @Override
    public Long getExamCount() {
        return examMapper.getExamCount();
    }

    @Override
    public Integer updateExam(Exam exam) {
        return examMapper.updateByPrimaryKey(exam);
    }

    @Override
    public PageInfo<Exam> getAllExamByCondition(Integer currentPage, Integer pageSize, String condition) {

        PageHelper.startPage(currentPage, pageSize);
        List<Exam> list;
        if (condition == null || condition.equals("")){//都为空
            list = examMapper.getAllExam();
            PageInfo<Exam> pageInfo = new PageInfo<>(list);
            return pageInfo;
        }else {
            list = examMapper.getExamByCondition(condition);
            PageInfo<Exam> pageInfo = new PageInfo<>(list);
            return pageInfo;
        }
    }

    @Override
    public List<ChartsVo> getAllExamForCharts(Integer examId) {
        List<ChartsVo> list = new ArrayList<>();
        if (examId == null){
            List<Exam> exams = examMapper.getExamListByExamFlag(2);//考试结束的试卷
            for (Exam exam : exams) {
                ChartsVo chartsVo = getChartsVoByExam(exam);
                list.add(chartsVo);
            }
        }else {
            Exam exam = examMapper.selectByPrimaryKey(examId);
            ChartsVo chartsVo = getChartsVoByExam(exam);
            list.add(chartsVo);
        }
        return list;
    }

    @Override
    public PageInfo<Grade> getGradesByCondition(Integer currentPage, Integer pageSize, String condition, String info,Integer examId) {
        //设置分页信息保存到threadLocal中
        PageHelper.startPage(currentPage, pageSize);//一定要放在查询之前
        List<Grade> list = new ArrayList<>();
        if ((info == null || info.equals("")) && (condition == null || condition.equals(""))) {
            //所有条件为空
            list = gradeMapper.getGradesByExamId(examId);
            PageInfo<Grade> pageInfo = new PageInfo<>(list);
            return pageInfo;
        }

        if ((info == null || info.equals("")) && (condition != null || !condition.equals(""))) {
            //查询条件为空
            list = gradeMapper.getGradesByExamId(examId);
            PageInfo<Grade> pageInfo = new PageInfo<>(list);
            return pageInfo;
        }

        //紧跟着的第一个select方法会被分页，userMapper会被PageInterceptor截拦,
        // 截拦器会从threadLocal中取出分页信息，把分页信息加到sql语句中，实现了分页查询
        //筛选grade并按照成绩由高到底排序
        if (condition.equals("1")) {
            //班级名称
            list = gradeMapper.getGradeForChartByGroupName(examId, info);
        } else if (condition.equals("2")) {
            //学号
            list = gradeMapper.getGradeForChartByUserName(examId, info);
        } else if (condition.equals("3")) {
            //姓名
            list = gradeMapper.getGradeForChartByName(examId,info);
        }

        PageInfo<Grade> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 获取modelVo的Comparator
     * @return
     */
    private Comparator<ModelVo> getModelVoComparator(){
        Comparator<ModelVo> comparator = (m1, m2) -> {
            if (m1.getModel().getType()!=m2.getModel().getType()){
                return m1.getModel().getType()-m2.getModel().getType();
            }else {
                if (!m1.getModel().getTitle().equals(m2.getModel().getTitle())){
                    return m1.getModel().getTitle().compareTo(m2.getModel().getTitle());
                }else {
                    return m1.getModel().getModelId()-m2.getModel().getModelId();
                }
            }
        };
        return comparator;
    }

    /**
     * 根据exam获取chartsVo
     * @param exam
     * @return
     */
    private ChartsVo getChartsVoByExam(Exam exam){
        ChartsVo chartsVo = new ChartsVo();
        List<Grade> gradeList = gradeMapper.getGradesByExamId(exam.getExamId());
        Float scoreSum = 0f;
        for (Grade grade : gradeList) {
            scoreSum += grade.getScore();
        }
        Float average = scoreSum / gradeList.size();
        //组装chartsVo
        chartsVo.setExam(exam);
        chartsVo.setList(gradeList);
        chartsVo.setAverage(average);
        return chartsVo;
    }
}
