package com.school.controller;

import com.github.pagehelper.PageInfo;
import com.school.entity.ChartsVo;
import com.school.entity.Exam;
import com.school.entity.Grade;
import com.school.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/charts")
public class ChartController {

    @Autowired
    private ExamService examService;

    @RequestMapping(value = "/getGrade",method = RequestMethod.GET)
    @ResponseBody
    public List<ChartsVo> getGrade(HttpServletRequest request){

        List<ChartsVo> list = examService.getAllExamForCharts(null);
        request.setAttribute("gradeList", list);
        return list;
    }

    @RequestMapping(value = "/getCharts", method = {RequestMethod.GET,RequestMethod.POST})
    public String getCharts(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
                             @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                             HttpServletRequest request) {
        PageInfo<Exam> pageInfo = examService.getAllExamByCondition(currentPage, pageSize, null);
        request.setAttribute("pageInfo", pageInfo);
        List<ChartsVo> list = examService.getAllExamForCharts(null);
        request.setAttribute("chartsList", list);
        return "forward:/page/examChart";
    }

    @RequestMapping(value = "/getGradeByExamId", method = {RequestMethod.GET, RequestMethod.POST})
    public String getGradeByExamId(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam("condition") String condition,
                             @RequestParam("info") String info,
                             @RequestParam("examId")Integer examId,
                             HttpServletRequest request) {
        Exam exam = examService.getExamById(examId);
        PageInfo<Grade> pageInfo = examService.getGradesByCondition(currentPage, pageSize, condition, info, examId);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("condition", condition);
        request.setAttribute("info", info);
        request.setAttribute("examId",examId);
        request.setAttribute("examName", exam.getExamName());
        return "forward:/page/gradeChart";
    }
}
