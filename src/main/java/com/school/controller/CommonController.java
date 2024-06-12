package com.school.controller;

import cn.hutool.extra.mail.MailUtil;
import com.school.entity.Admin;
import com.school.entity.Role;
import com.school.entity.User;
import com.school.service.AdminService;
import com.school.service.UserService;
import com.school.utils.MD5Utils;
import com.school.utils.RoleUtil;
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
@RequestMapping(value = "/common")
public class CommonController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    //修改信息
    @RequestMapping(value = "/updateInfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> commonUpdateInfo(
            @RequestParam("name")String name,
            @RequestParam("email")String email,
            HttpSession session){
        Role role = (Role) session.getAttribute("role");
        if (role == null){
            return null;
        }
        Map<String,Object> map = new HashMap<>();
        if (role.getType().equals(RoleUtil.RoleType.ADMIN)){//管理员
            Admin admin = (Admin) session.getAttribute("admin");
            admin.setAdminName(name);
            admin.setAdminEmail(email);
            Integer result = adminService.updateAdmin(admin);
            if (result == 1){
                map.put("commonUpdateInfo", "信息修改成功！");
                //需要更新session的信息
                Admin a = adminService.getAdminById(role.getId());
                Role r = new Role(a, role.getType());
                session.setAttribute("role", r);
                return map;
            }else {
                map.put("commonUpdateInfo", "信息修改失败！请联系管理员...");
                return map;
            }
        }else {//用户
            User user = (User) session.getAttribute("user");
            user.setName(name);
            user.setEmail(email);
            Integer result = userService.updateUser(user);
            if (result == 1){
                map.put("commonUpdateInfo", "信息修改成功！");
                //需要更新session的信息
                User u = userService.getUserById(role.getId());
                Role r = new Role(u, role.getType());
                session.setAttribute("role", r);
                return map;
            }else {
                map.put("commonUpdateInfo", "信息修改失败！请联系管理员...");
                return map;
            }
        }
    }

    //修改密码
    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> adminUpdatePassword(
            @RequestParam("old_password")String old_password,
            @RequestParam("new_password")String new_password,
            HttpSession session){
        Role role = (Role) session.getAttribute("role");
        if (role == null){
            return null;
        }
        Map<String,Object> map = new HashMap<>();
        if (role.getType().equals(RoleUtil.RoleType.ADMIN)){
            Admin admin = (Admin) session.getAttribute("admin");
            String password = admin.getAdminPassword();
            if (!password.equals(MD5Utils.md5(old_password))){
                map.put("commonUpdatePassword", "原密码错误！");
                return map;
            }
            admin.setAdminPassword(MD5Utils.md5(new_password));
            Integer result = adminService.updateAdmin(admin);
            if (result == 1){
                map.put("commonUpdatePassword", "修改密码成功！");
            }else {
                map.put("commonUpdatePassword", "修改密码失败！请联系管理员...");
            }
            return map;
        }else {
            User user = (User) session.getAttribute("user");
            String password = user.getPassword();
            if (!password.equals(MD5Utils.md5(old_password))){
                map.put("commonUpdatePassword", "原密码错误！");
                return map;
            }
            user.setPassword(MD5Utils.md5(new_password));
            Integer result = userService.updateUser(user);
            if (result == 1){
                map.put("commonUpdatePassword", "修改密码成功！");
            }else {
                map.put("commonUpdatePassword", "修改密码失败！请联系管理员...");
            }
            return map;
        }
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String commonLogout(HttpSession session){
        Role role = (Role) session.getAttribute("role");
        if (role != null){
            //账号注销
            session.removeAttribute("user");
            session.removeAttribute("role");
            session.removeAttribute("admin");
        }
        return "redirect:/page/login";
    }

    @RequestMapping(value = "/sendEmail",method = RequestMethod.POST)
    public String userSendEmail(@RequestParam("email")String email, HttpServletRequest request){

        User user = userService.getUserByEmail(email);
        if (user == null){
            request.setAttribute("userSendEmail","邮箱不存在，请重新输入邮箱！");
        }else {
            String subject = "用户重置密码";
            String resetPassHref = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+ "/page/rePassword?username=" + user.getUsername();
            String emailContent = "请勿回复本邮件.点击下面的链接,重设密码<br/><a href="
                    + resetPassHref + " target='_BLANK'>" + resetPassHref
                    + "</a>  或者    <a href=" + resetPassHref
                    + " target='_BLANK'>点击我重新设置密码</a>"
                    + "<br/>tips:请注意保存密码,防止泄露,感谢您对轻考试的支持,祝您生活愉快";
            MailUtil.send(email, subject, emailContent, true);
            request.setAttribute("userSendEmail", "重置密码邮件已经发送，请登陆邮箱进行重置！");
        }
        return "forward:/page/forgot";
    }

    //用户修改密码
    @RequestMapping(value = "/userRePassword",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> userRePassword(
            @RequestParam("username")String username,
            @RequestParam("password")String password){
        Map<String,Object> map = new HashMap<>();
        User user = userService.getUserByUserName(username);
        if (user == null){
            map.put("userRePassword", "修改密码失败！请联系管理员...");
            return map;
        }
        user.setPassword(MD5Utils.md5(password));
        Integer result = userService.updateUser(user);
        if (result == 1){
            map.put("userRePassword", "修改密码成功！");
        }else {
            map.put("userRePassword", "修改密码失败！请联系管理员...");
        }
        return map;

    }


    @RequestMapping(value = "/adminSendEmail",method = RequestMethod.POST)
    public String adminSendEmail(@RequestParam("email")String email, HttpServletRequest request){

        Admin admin = adminService.getAdminByEmail(email);
        if (admin == null){
            request.setAttribute("adminSendEmail","邮箱不存在，请重新输入邮箱！");
        }else {
            String subject = "用户重置密码";
            String resetPassHref = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+ "/page/adminRePassword?username=" + admin.getAdminUsername();
            String emailContent = "请勿回复本邮件.点击下面的链接,重设密码<br/><a href="
                    + resetPassHref + " target='_BLANK'>" + resetPassHref
                    + "</a>  或者    <a href=" + resetPassHref
                    + " target='_BLANK'>点击我重新设置密码</a>"
                    + "<br/>tips:请注意保存密码,防止泄露,感谢您对轻考试的支持,祝您生活愉快";
            MailUtil.send(email, subject, emailContent, true);
            request.setAttribute("adminSendEmail", "重置密码邮件已经发送，请登陆邮箱进行重置！");
        }
        return "forward:/page/adminForgot";
    }

    //管理员修改密码
    @RequestMapping(value = "/adminRePassword",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> adminRePassword(
            @RequestParam("username")String username,
            @RequestParam("password")String password){
        Map<String,Object> map = new HashMap<>();
        Admin admin = adminService.getAdminByUserName(username);
        if (admin == null){
            map.put("adminRePassword", "修改密码失败！请联系管理员...");
            return map;
        }
        admin.setAdminPassword(MD5Utils.md5(password));
        Integer result = adminService.updateAdmin(admin);
        if (result == 1){
            map.put("adminRePassword", "修改密码成功！");
        }else {
            map.put("adminRePassword", "修改密码失败！请联系管理员...");
        }
        return map;

    }
}
