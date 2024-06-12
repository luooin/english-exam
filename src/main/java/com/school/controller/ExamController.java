package com.school.controller;

import com.github.pagehelper.PageInfo;
import com.school.entity.*;
import com.school.manager.ExampleManager;
import com.school.service.ExamService;
import com.school.service.GradeService;
import com.school.service.ModelService;
import com.school.service.RecordService;
import com.school.utils.HtmlUtil;
import com.school.utils.ModelTypeUtil;
import com.school.utils.RoleUtil;
import com.school.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping(value = "/exam")
public class ExamController {

    @Autowired
    private ExamService examService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private ExampleManager exampleManager;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addExam(
            @RequestParam("exam_name") String exam_name,
            @RequestParam("need_time") String need_time,
            @RequestParam("exam_author") String exam_author,
            @RequestParam("exam_type") Integer exam_type,
            HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        List<Exam> examExist = examService.getExamByCondition(exam_name);
        if (examExist.size() > 0) {
            map.put("addExam", "试卷名称已存在！");
            return map;
        }
        Role role = (Role) session.getAttribute("role");
        Example example = exampleManager.getExample(role.getId());
        List<ModelVo> modelVoList = examService.manageModel(example.getModelVoSet());
        Exam exam = new Exam();
        exam.setExamName(exam_name);//试卷名称
        exam.setExamType(exam_type);//默认组卷类型0
        exam.setNeedTime(need_time);//所需时间
        exam.setExamAuthor(exam_author);//发布者
        String model_ids = "";//试题ids
        String exam_content = "";//试卷内容
        String exam_answer = "";//试卷答案
        String exam_analysis = "";//试卷解析
        float exam_grade = 0;//试卷总分
        for (ModelVo modelVo : modelVoList) {
            model_ids += modelVo.getModel().getModelId() + ",";
            exam_content += modelVo.getModel().getContent() + modelVo.getModel().getModelOption();
            exam_answer += modelVo.getModel().getAnswer() + "|";
            exam_analysis += modelVo.getModel().getAnalysis();
            exam_grade = exam_grade + modelVo.getModel().getGrade();
        }
        exam.setModelIds(model_ids);
        exam.setExamContent(exam_content);
        exam.setExamAnswer(exam_answer);
        exam.setExamAnalysis(exam_analysis);
        if (exam_type == 1){
            exam.setExamGrade(497f);//默认总分
        }else {
            exam.setExamGrade(exam_grade);
        }
        exam.setExamFlag(0);//默认考试未开启
        Integer result = examService.insertExam(exam);
        if (result == 1) {
            map.put("addExam", "试卷添加成功！");
            //清除idsSet和modelVoSet内存中的数据
            example.getIdsSet().clear();
            example.getModelVoSet().clear();
            return map;
//            return "redirect:/exam/select";
        }
        map.put("addExam", "试卷添加失败！请联系管理员...");
//        return "redirect:/page/exam/add";
        return map;
    }

    @RequestMapping(value = "/select", method = {RequestMethod.GET,RequestMethod.POST})
    public String getAllExam(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
                             @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                             @RequestParam("exam_name") String exam_name,
                             HttpServletRequest request, HttpSession session) {
        PageInfo<Exam> pageInfo = examService.getAllExamByCondition(currentPage, pageSize, exam_name);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("exam_name",exam_name);
        Role role = (Role) session.getAttribute("role");
        if (role.getType() == RoleUtil.RoleType.STUDENT) {
            return "forward:/page/studentStart";
        }else if (role.getType() == RoleUtil.RoleType.TEACHER){
            return "forward:/page/teacher_exam_list";
        }
        return "forward:/page/exam_list";
    }

    @RequestMapping(value = "/getModelVoList", method = RequestMethod.GET)
    public String getModelVoList(@RequestParam("examId") Integer examId,HttpServletRequest request) {
        Exam exam = examService.getExamById(examId);
        if (exam == null) {
            return null;
        }
        String[] ids = exam.getModelIds().split(",");
        List<ModelVo> modelVoList = new ArrayList<>();
        for (String s : ids) {
            ModelVo modelVo = modelService.getModelVo(Integer.parseInt(s));
            modelVoList.add(modelVo);
        }
        request.setAttribute("exam", exam);
        request.setAttribute("modelVoList",modelVoList);
        return "forward:/page/examInfo";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteExam(@RequestParam("examId") Integer examId) {
        Map<String, Object> map = new HashMap<>();
        if (examId == null || examId.equals("")) {
            map.put("deleteExam", "删除失败！请联系管理员...");
            return map;
        }
        Integer result = examService.deleteExam(examId);
        if (result == 1) {
            map.put("deleteExam", "试卷删除成功！");
        } else {
            map.put("deleteExam", "删除失败！请联系管理员...");
        }
        return map;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateExam(@RequestParam("examId") Integer examId) {
        Map<String, Object> map = new HashMap<>();
        if (examId == null || examId.equals("")) {
            map.put("updateExam", "操作失败！请联系管理员...");
            return map;
        }
        Exam exam = examService.getExamById(examId);
        if (exam.getExamFlag() == 0) {
            exam.setExamFlag(1);
        } else if (exam.getExamFlag() == 1){
            exam.setExamFlag(2);
        }
        Integer result = examService.updateExam(exam);
        if (result == 1) {
            map.put("updateExam", "试卷操作成功！");
        } else {
            map.put("updateExam", "操作失败！请联系管理员...");
        }
        return map;
    }

    @RequestMapping(value = "/getExam", method = RequestMethod.GET)
    public String getExam(@RequestParam("exam_id") Integer exam_id, HttpSession session,HttpServletRequest request) {

        Role role = (Role) session.getAttribute("role");
        Example example = exampleManager.getExample(role.getId());
        Exam exam = examService.getExamById(exam_id);
        //判断试卷是否为随机组卷
        if (exam.getExamType() == 1){
            //随机
            if (example.getModelMap().size() == 0){
                //检查ModelMap是否为空
                example = randomModelComposeExam(role);
                List<ModelVo> list = examService.manageModel(example.getModelVoSet());
                for (int i = 1; i <= list.size(); i++) {
                    example.getModelMap().put(i, list.get(i-1));
                }
            }
        }else {
            //固定
            String[] modelIds = exam.getModelIds().split(",");
            for (int i = 1; i <= modelIds.length; i++) {
                ModelVo modelVo = modelService.getModelVo(Integer.parseInt(modelIds[i - 1]));
                if (modelVo != null){
                    example.getModelMap().put(i, modelVo);
                }
            }
        }

//        session.setAttribute("time", Float.valueOf(exam.getNeedTime()) * 60);
        session.setAttribute("size", example.getModelMap().size());
        session.setAttribute("exam", exam);
        example.setNum(1);//默认第一题
        //计算当前时间以及考试结束时间
        if (example.getStartTime() == null) {
            example.setStartTime(System.currentTimeMillis());
            example.setEndTime(example.getStartTime() + Long.parseLong(exam.getNeedTime()) * 60 * 1000);
        }
        //获取剩余时间
//        Long timeCountDown = TimeUtil.getTimeCountDown(System.currentTimeMillis(), example.getEndTime());
        session.setAttribute("time",TimeUtil.getTimeCountDown(System.currentTimeMillis(),example.getEndTime()));
        session.setAttribute("num", example.getNum());
        session.setAttribute("modelVo", example.getModelMap().get(1));//第一题
        return "forward:/page/examPage";
    }

    @RequestMapping(value = "/saveAnswer", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getModelVo(
            @RequestParam("key") Integer key,
            @RequestParam("answer") String answer,
            HttpSession session,HttpServletRequest request) {
        Role role = (Role) session.getAttribute("role");
        Example example = exampleManager.getExample(role.getId());
        Map<String, Object> map = new HashMap<>();
        example.getAnswerMap().put(example.getNum(), answer);
        example.setNum(key);
        //获取剩余时间
        Long timeCountDown = TimeUtil.getTimeCountDown(System.currentTimeMillis(), example.getEndTime());
        session.setAttribute("time",TimeUtil.getTimeCountDown(System.currentTimeMillis(),example.getEndTime()));
        session.setAttribute("num", example.getNum());
        session.setAttribute("answer", example.getAnswerMap().get(key));
        session.setAttribute("modelVo", example.getModelMap().get(key));
        map.put("saveAnswer", "答案保存成功！");
        return map;
    }

    @Transactional
    @RequestMapping(value = "/saveExam", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveExam(
            @RequestParam("num") Integer num,
            @RequestParam("answer") String answer,
            HttpSession session
    ) {
        Map<String, Object> map = new HashMap<>();
        Role role = (Role) session.getAttribute("role");
        Example example = exampleManager.getExample(role.getId());
        example.getAnswerMap().put(num, answer);
        //比对答案

        Exam exam = (Exam) session.getAttribute("exam");
        String username = role.getUsername();//用户名
        String name = role.getName();//姓名
        String title = exam.getExamName();//试卷名称
        Integer examId = exam.getExamId();//试卷id
        String groupName = "15070842";//班级名称    暂不处理
//        Long startExamTime = (Long) session.getAttribute("startTime");//考试开始时间
        String spendTime = TimeUtil.getTimeReduce(example.getStartTime(), System.currentTimeMillis());//考试所用时间
        Float score = 0f;
        for (Map.Entry<Integer, ModelVo> entry : example.getModelMap().entrySet()) {
            Integer key = entry.getKey();
            ModelVo modelVo = entry.getValue();
            //创建考试试题记录，方便以后查看
            Record record = new Record();
            record.setUsername(username);
            record.setName(name);
            record.setExamId(examId);
            record.setModelId(modelVo.getModel().getModelId());
            record.setAnswer(example.getAnswerMap().get(key));
            recordService.insertRecord(record);

            if (modelVo.getModel().getType() == ModelTypeUtil.Type.TRANSLATE.getValue() || modelVo.getModel().getType() == ModelTypeUtil.Type.WRITING.getValue()) {
                continue;
            }
            String[] correctAnswer = modelVo.getModel().getAnswer().split("\\|");
            Float perGrade = modelVo.getModel().getGrade() / correctAnswer.length;
            //处理userInput

            String userInput = HtmlUtil.delHTMLTag(example.getAnswerMap().get(key)).trim();
            String[] userAnswer = HtmlUtil.format(userInput).split("(?<!^)(?=[A-Z])");

            //用户提交的answer
            String[] submitAnswer = new String[correctAnswer.length];
            if (userAnswer.length > submitAnswer.length){
                System.arraycopy(userAnswer, 0, submitAnswer, 0, submitAnswer.length);
            }else {
                System.arraycopy(userAnswer, 0, submitAnswer, 0, userAnswer.length);
            }
            for (int i = 0; i < correctAnswer.length; i++) {
                if (correctAnswer[i].equals(submitAnswer[i])) {
                    score += perGrade;
                }
            }
        }
        //创建考卷信息grade
        Grade grade = new Grade();
        grade.setUsername(username);
        grade.setName(name);
        grade.setTitle(title);
        grade.setGroupName(groupName);
        grade.setSpendTime(spendTime);
        grade.setScore(score);
        grade.setExamId(examId);
        grade.setFlag(0);
        Integer result = gradeService.insertGrade(grade);
        if (result == 1){
            map.put("saveExam", "试卷提交成功！");
            //清除考试开始时间
            example.setStartTime(null);
            session.removeAttribute("time");
            //清除上次考试答题记录
            example.getAnswerMap().clear();
            example.getModelMap().clear();
        }
        return map;
    }

    @RequestMapping(value = "/getALLModel",method = {RequestMethod.POST,RequestMethod.GET})
    public String getAddModelList(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
                                  @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                  @RequestParam("type") Integer type,
                                  @RequestParam("title") String title, HttpSession session){

        PageInfo<ModelVo> pageInfo = modelService.selectByTypeAndTitle(currentPage, pageSize, type, title);
        addFlag(session, pageInfo);//设置是否为已添加试题的标志
        Role role = (Role) session.getAttribute("role");
        Example example = exampleManager.getExample(role.getId());
        session.setAttribute("currentPage",currentPage);
        session.setAttribute("pageSize",pageSize);
        session.setAttribute("pageInfo", pageInfo);
        session.setAttribute("pageType", type);
        session.setAttribute("title",title);
        session.setAttribute("list", new ArrayList<>(example.getModelVoSet()));
        return "forward:/page/add_model_list";
    }

    @RequestMapping(value = "/saveIds",method = RequestMethod.GET)
    public String saveIds(@RequestParam("modelId") Integer modelId,HttpSession session){
        Role role = (Role) session.getAttribute("role");
        Example example = exampleManager.getExample(role.getId());
        example.getIdsSet().add(modelId);
        example.getModelVoSet().add(modelService.getModelVo(modelId));
        //更新页面信息
        return get_exam_model_list(session);
    }

    @RequestMapping(value = "/removeIds",method = RequestMethod.GET)
    public String removeIds(@RequestParam("modelId") Integer modelId,HttpSession session){
        Role role = (Role) session.getAttribute("role");
        Example example = exampleManager.getExample(role.getId());

        example.getIdsSet().remove(modelId);
        example.getModelVoSet().remove(modelService.getModelVo(modelId));
        //更新页面信息
        return get_exam_model_list(session);
    }

    /**添加试卷的时候
     * 添加或者删除model时，刷新页面的记录
     * @param session
     * @return
     */
    public String get_exam_model_list(HttpSession session){

        Role role = (Role) session.getAttribute("role");
        Example example = exampleManager.getExample(role.getId());
        Integer currentPage = (Integer) session.getAttribute("currentPage");
        Integer pageSize = (Integer) session.getAttribute("pageSize");
        Integer type = (Integer) session.getAttribute("pageType");
        String title = (String) session.getAttribute("title");

        PageInfo<ModelVo> pageInfo = modelService.selectByTypeAndTitle(currentPage, pageSize, type, title);

        addFlag(session,pageInfo);//设置是否为已添加试题的标志

        session.setAttribute("pageInfo", pageInfo);
        session.setAttribute("list", new ArrayList<>(example.getModelVoSet()));
        return "forward:/page/add_model_list";
    }

    /**
     * 设置是否为已添加试题的标志
     * @param pageInfo
     */
    public void addFlag(HttpSession session,PageInfo<ModelVo> pageInfo){
        Role role = (Role) session.getAttribute("role");
        Example example = exampleManager.getExample(role.getId());
        for (ModelVo modelVo:pageInfo.getList()){
            if (example.getIdsSet().contains(modelVo.getModel().getModelId())){
                modelVo.setAddFlag(1);
            }
        }
    }



    @RequestMapping(value = "/autoCompose",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> autoCompose(HttpSession session){
        Map<String,Object> map = new HashMap<>();
        Role role = (Role) session.getAttribute("role");
        Example example = randomModelComposeExam(role);
        map.put("autoCompose","系统已为您自动组卷");
        return map;
    }


    /**
     * 随机抽取数据库中的试题组成试卷
     * @param role
     * @return
     */
    private Example randomModelComposeExam(Role role){
        Example example = exampleManager.getExample(role.getId());
        //随机题库中的数据
        List<ModelVo> modelVoList = modelService.selectAll();
        List<ModelVo> blankModelVoList = new ArrayList<>();//选词填空
        List<ModelVo> paragraphModelVoList = new ArrayList<>();//段落匹配
        List<ModelVo> readingModelVoList = new ArrayList<>();//仔细阅读
        List<ModelVo> translateModelVoList = new ArrayList<>();//翻译
        List<ModelVo> writingModelVoList = new ArrayList<>();//写作
        for (ModelVo modelVo : modelVoList) {
            if (modelVo.getModel().getType() == ModelTypeUtil.Type.BLANK.getValue()){

                blankModelVoList.add(modelVo);
            }else if (modelVo.getModel().getType() == ModelTypeUtil.Type.PARAGRAPH.getValue()){

                paragraphModelVoList.add(modelVo);
            }else if (modelVo.getModel().getType() == ModelTypeUtil.Type.READING.getValue()){

                readingModelVoList.add(modelVo);
            }else if (modelVo.getModel().getType() == ModelTypeUtil.Type.TRANSLATE.getValue()){

                translateModelVoList.add(modelVo);
            }else if (modelVo.getModel().getType() == ModelTypeUtil.Type.WRITING.getValue()){

                writingModelVoList.add(modelVo);
            }
        }
        //随机各个类型的列表
        Random random = new Random(47);
        Collections.shuffle(blankModelVoList, random);
        Collections.shuffle(paragraphModelVoList, random);
        Collections.shuffle(readingModelVoList, random);
        Collections.shuffle(translateModelVoList, random);
        Collections.shuffle(writingModelVoList, random);

        example.getModelVoSet().clear();//清空数据
        //添加数据
        example.getModelVoSet().add(blankModelVoList.get((int) (Math.random()* blankModelVoList.size())));//获取的时候再次随机
        example.getModelVoSet().add(paragraphModelVoList.get(getIndex(paragraphModelVoList)));
        example.getModelVoSet().add(readingModelVoList.get(getIndex(readingModelVoList)));
        example.getModelVoSet().add(readingModelVoList.get(getIndex(readingModelVoList)));
        example.getModelVoSet().add(translateModelVoList.get(getIndex(translateModelVoList)));
        example.getModelVoSet().add(writingModelVoList.get(getIndex(writingModelVoList)));

        while (example.getModelVoSet().size() < 6){
            //避免阅读理解添加重复
            example.getModelVoSet().add(readingModelVoList.get(getIndex(readingModelVoList)));
        }
        //把modelId添加到IdsSet
        example.getIdsSet().clear();
        for (ModelVo modelVo : example.getModelVoSet()) {
            example.getIdsSet().add(modelVo.getModel().getModelId());
        }
        return example;
    }


    /**
     * 随机list的索引
     * @param list
     * @return
     */
    private int getIndex(List<ModelVo> list){
        int index = (int) (Math.random()* list.size());
        return index;
    }
}
