package com.school.controller;

import com.github.pagehelper.PageInfo;
import com.school.entity.*;
import com.school.service.ExamService;
import com.school.service.GradeService;
import com.school.service.ModelService;
import com.school.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;
    @Autowired
    private ExamService examService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private ModelService modelService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(
            @RequestParam("username") String username,
            @RequestParam("examId") Integer examId,
            HttpSession session) {
        Role role = (Role) session.getAttribute("role");
        Map<String, Object> map = new HashMap<>();
        if (!role.getUsername().equals(username)) {
            //信息输入错误
            map.put("loginExam", "信息输入有误！");
            return map;
        }
        //判断考生是否在考试行列中
        Grade grade = gradeService.getGradeByExamId(username, examId);
        if (grade != null) {
            //考生已考试
            map.put("loginExam", "您已参加考试，无法进行第二次考试！");
            return map;
        }
        map.put("loginExam", "success");
        return map;
    }

    //学生查询
    @RequestMapping(value = "/getGrade", method = {RequestMethod.POST, RequestMethod.GET})
    public String getGradeByCondition(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                      @RequestParam("title") String title,
                                      HttpSession session,
                                      HttpServletRequest request) {
        Role role = (Role) session.getAttribute("role");
        PageInfo<Grade> pageInfo = gradeService.getGradeByUserNameAndTitle(currentPage, pageSize, role.getUsername(), title);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("title", title);
        return "forward:/page/studentTable";
    }

    //admin和teacher查找
    @RequestMapping(value = "/getGradeVo", method = {RequestMethod.GET, RequestMethod.POST})
    public String getGradeVo(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam("condition") String condition,
                             @RequestParam("info") String info,
                             HttpServletRequest request) {
        PageInfo<Grade> pageInfo = gradeService.getAllGradeByCondition(currentPage, pageSize, condition, info);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("condition", condition);
        request.setAttribute("info", info);

        return "forward:/page/record_list";
    }

    @RequestMapping(value = "/updateGrade", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateGrade(@RequestParam("gradeId") Integer gradeId, @RequestParam("score") Float score) {
        Map<String, Object> map = new HashMap<>();
        Grade grade = gradeService.getGradeById(gradeId);
        Float examScore = 0f;
        if (grade.getScore() > score) {
            examScore = grade.getScore() + score;
        } else {
            examScore = score;
        }
        grade.setScore(examScore);
        grade.setFlag(1);//修改判卷标识符
        Integer result = gradeService.updateGradeById(grade);
        if (result == 1) {
            map.put("updateGrade", "判卷成功！");
        } else {
            map.put("updateGrade", "判卷失败！请联系管理员...");
        }
        return map;
    }
}
