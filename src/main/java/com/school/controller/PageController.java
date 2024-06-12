package com.school.controller;

import com.school.entity.Role;
import com.school.utils.RoleUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page")
public class PageController {

    //是否有权限操作
    public String isAdmin(HttpSession session){
        Role role = (Role) session.getAttribute("role");
        if (role == null){
            return "redirect:/page/login";
        }else if (role.getType().equals(RoleUtil.RoleType.STUDENT)){
            return "redirect:/page/login";
        }
        return "success";
    }
    //first

    @RequestMapping(value = {"/login","/","/page"})
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value = "/register")
    public String toRegister() {
        return "register";
    }

    @RequestMapping(value = "/forgot")
    public String toForgot() {
        return "forgot";
    }

    //admin

    @RequestMapping(value = "/adminLogin")
    public String toAdminLogin() {
        return "admin_login";
    }

    @RequestMapping(value = "/rePassword")
    public String toEmailRePassword() {
        return "email_rePassword";
    }
    @RequestMapping(value = "/adminRePassword")
    public String toAdminEmailRePassword() {
        return "admin_email_rePassword";
    }

    @RequestMapping(value = "/adminForgot")
    public String toAdminForgot() {
        return "admin_forgot";
    }

    //admin、teacher

    @RequestMapping(value = "/adminTable")
    public String toAdminTable(HttpSession session) {
        String result = isAdmin(session);
        if (result.equals("success")){
            return "admin_table";
        }else {
            return result;
        }
    }

    @RequestMapping(value = "/adminIndex")
    public String toAdminIndex(HttpSession session) {
        String result = isAdmin(session);
        if (result.equals("success")){
            return "admin_index";
        }else {
            return result;
        }
    }

    @RequestMapping(value = "/examChart")
    public String toExamChart(HttpSession session) {
        String result = isAdmin(session);
        if (result.equals("success")){
            return "exam_chart";
        }else {
            return result;
        }
    }

    @RequestMapping(value = "/gradeChart")
    public String toGradeChart(HttpSession session) {
        String result = isAdmin(session);
        if (result.equals("success")){
            return "grade_chart";
        }else {
            return result;
        }
    }

    @RequestMapping(value = "/addModel")
    public String toAddModel(HttpSession session) {
        String result = isAdmin(session);
        if (result.equals("success")){
            return "add_model";
        }else {
            return result;
        }
    }

    @RequestMapping(value = "/modelList")
    public String toModelList(HttpSession session) {
        String result = isAdmin(session);
        if (result.equals("success")){
            return "model_list";
        }else {
            return result;
        }
    }

    @RequestMapping(value = "/add_model_list")
    public String toAddModelList(HttpSession session) {
        String result = isAdmin(session);
        if (result.equals("success")){
            return "add_model_list";
        }else {
            return result;
        }
    }

    @RequestMapping(value = "/teacherIndex")
    public String toTeacherIndex() {
        return "teacher_index";
    }

    @RequestMapping(value = "/teacherTable")
    public String toTeacherTable(HttpSession session) {
        String result = isAdmin(session);
        if (result.equals("success")){
            return "teacher_table";
        }else {
            return result;
        }
    }

    @RequestMapping(value = "/addExam")
    public String toAddExam(HttpSession session) {
        String result = isAdmin(session);
        if (result.equals("success")){
            return "add_exam";
        }else {
            return result;
        }
    }
    @RequestMapping(value = "/exam_list")
    public String toExamList(HttpSession session){
        String result = isAdmin(session);
        if (result.equals("success")){
            return "exam_list";
        }else {
            return result;
        }
    }

    @RequestMapping(value = "/teacher_exam_list")
    public String toTeacherExamList(HttpSession session){
        String result = isAdmin(session);
        if (result.equals("success")){
            return "teacher_exam_list";
        }else {
            return result;
        }
    }

    @RequestMapping(value = "/record_list")
    public String toRecordList(HttpSession session){
        String result = isAdmin(session);
        if (result.equals("success")){
            return "record_list";
        }else {
            return result;
        }
    }

    @RequestMapping(value = "/recordPage")
    public String toRecordPage(HttpSession session){
        String result = isAdmin(session);
        if (result.equals("success")){
            return "recordPage";
        }else {
            return result;
        }
    }

    @RequestMapping(value = "/examInfo")
    public String toExamInfo(HttpSession session){
        String result = isAdmin(session);
        if (result.equals("success")){
            return "examInfo";
        }else {
            return result;
        }
    }

    @RequestMapping(value = "/settings")
    public String toSettings(HttpSession session){
        String result = isAdmin(session);
        if (result.equals("success")){
            return "settings";
        }else {
            return result;
        }
    }

    @RequestMapping(value = "/updatePassword")
    public String toUpdatePassword(HttpSession session){
        String result = isAdmin(session);
        if (result.equals("success")){
            return "update_password";
        }else {
            return result;
        }
    }

    //student

    @RequestMapping(value = "/studentIndex")
    public String toStudentIndex() {
        return "studentIndex";
    }

    @RequestMapping(value = "/studentStart")
    public String toStudentStart() {
        return "studentStart";
    }

    @RequestMapping(value = "/studentMistakes")
    public String toStudentMistake() {
        return "studentMistakes";
    }

    @RequestMapping(value = "/studentTable")
    public String toStudentTable() {
        return "studentTable";
    }

    @RequestMapping(value = "/indexPage")
    public String toIndexPage() {
        return "indexPage";
    }

    @RequestMapping(value = "/examPage")
    public String toExamPage() {
        return "examPage";
    }

    @RequestMapping(value = "/student_record_page")
    public String toStudentRecordPage() {
        return "student_record_page";
    }

    @RequestMapping(value = "/student_settings")
    public String toStudentSettings(){
        return "student_settings";
    }

    @RequestMapping(value = "/student_update_password")
    public String toStudentUpdatePassword(){
        return "student_update_password";
    }

    @RequestMapping(value = "edit")
    public String toDemo(){
        return "edit";
    }
}
