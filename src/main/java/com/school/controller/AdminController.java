package com.school.controller;

import com.school.entity.Admin;
import com.school.entity.Exam;
import com.school.entity.Example;
import com.school.entity.Role;
import com.school.manager.ExampleManager;
import com.school.service.AdminService;
import com.school.service.ExamService;
import com.school.service.ModelService;
import com.school.utils.MD5Utils;
import com.school.utils.RoleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private ExamService examService;
    @Autowired
    private ModelService modelService;

    @Autowired
    private ExampleManager exampleManager;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String adminLogin(@RequestParam("adminUserName") String adminUserName,
                             @RequestParam("adminPassword") String adminPassword,
                             HttpSession session) {
        String password = MD5Utils.md5(adminPassword);
        Admin admin = adminService.login(adminUserName, password);
        if (admin != null) {
            Map<String,Object> map = new HashMap<>();
            List<Exam> list = examService.getExamRecently();
            Long examCount = examService.getExamCount();
            Long modelCount = modelService.getModelCount();
            map.put("examCount",examCount);
            map.put("modelCount", modelCount);
            session.setAttribute("map", map);
            session.setAttribute("list", list);
            //前端显示
            Role role = new Role(admin, RoleUtil.RoleType.ADMIN);//管理员为100

            session.setAttribute("role", role);
            session.setAttribute("admin", admin);
            //在内存中保存数据
            if (exampleManager.getExample(admin.getAdminId()) == null){
                exampleManager.putExampleMap(admin.getAdminId(),new Example());
            }
            return "redirect:/page/adminIndex";
        }
        return "redirect:/page/adminLogin";
    }

    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String adminLogout(HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null){
            //账号注销
            session.removeAttribute("admin");
            session.removeAttribute("role");
        }
        return "redirect:/page/adminLogin";
    }

    @RequestMapping(value = "updatePassword",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> adminUpdatePassword(
            @RequestParam("old_password")String old_password,
            @RequestParam("new_password")String new_password,
            HttpSession session
            ){
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null){
           return null;
        }
        Map<String,Object> map = new HashMap<>();
        String password = admin.getAdminPassword();
        if (!password.equals(MD5Utils.md5(old_password))){
            map.put("updatePassword", "原密码错误！");
            return map;
        }
        admin.setAdminPassword(MD5Utils.md5(new_password));
        Integer result = adminService.updateAdmin(admin);
        if (result == 1){
            map.put("updatePassword", "修改密码成功！");
        }else {
            map.put("updatePassword", "修改密码失败！请联系管理员...");
        }
        return map;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> adminUpdate(
            @RequestParam("name")String name,
            @RequestParam("email")String email,
            HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null){
            return null;
        }
        Map<String,Object> map = new HashMap<>();
        admin.setAdminName(name);
        admin.setAdminEmail(email);
        Integer result = adminService.updateAdmin(admin);
        if (result == 1){
            map.put("updateAdmin", "信息修改成功！");
            return map;
        }else {
            map.put("updateAdmin", "信息修改失败！请联系管理员...");
            return map;
        }
    }
}
