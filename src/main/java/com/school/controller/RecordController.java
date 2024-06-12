package com.school.controller;

import com.school.entity.*;
import com.school.service.GradeService;
import com.school.service.ModelService;
import com.school.service.RecordService;
import com.school.utils.RoleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/record")
public class RecordController {

    @Autowired
    private GradeService gradeService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private ModelService modelService;


    @RequestMapping(value = "/getRecordVo", method = RequestMethod.GET)
    public String getRecordVo(@RequestParam("gradeId") Integer gradeId, HttpServletRequest request, HttpSession session) {

        Role role = (Role) session.getAttribute("role");
        //显示考生记录
        Grade grade = gradeService.getGradeById(gradeId);//考生记录
        List<RecordVo> recordVoList = new ArrayList<>();//试题内容记录
        String username = grade.getUsername();
        Integer examId = grade.getExamId();
        List<Record> recordList = recordService.getRecordList(username, examId);//获取试题记录
        for (Record record : recordList) {

            Integer modelId = record.getModelId();//获取model id
            ModelVo modelVo = modelService.getModelVo(modelId);
            //构造考试记录
            RecordVo recordVo = new RecordVo(modelVo,record);//考试记录
            //填充答卷记录
            recordVoList.add(recordVo);
        }
        request.setAttribute("grade", grade);
        request.setAttribute("recordVoList",recordVoList);
        if (role.getType() == RoleUtil.RoleType.STUDENT){
            return "forward:/page/student_record_page";
        }
        return "forward:/page/recordPage";
    }
}
