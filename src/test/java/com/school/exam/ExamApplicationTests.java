package com.school.exam;

import com.school.entity.Exam;
import com.school.service.ExamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamApplicationTests {

    @Autowired
    private ExamService examService;

    @Test
    public void insertExam() {
        Exam exam = new Exam();
        exam.setExamName("test");
        exam.setExamContent("");
        exam.setExamType(1);//随机
        exam.setNeedTime("150");
        exam.setExamAuthor("test");
        exam.setExamAnswer("");
        exam.setExamAnalysis("");
        exam.setExamGrade(497.0f);
        exam.setModelIds("");
        exam.setExamFlag(0);
        examService.insertExam(exam);
    }

}
