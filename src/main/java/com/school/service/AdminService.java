package com.school.service;

import com.school.entity.Admin;

public interface AdminService {

    //登录
    Admin login(String adminUserName, String adminPassword);

    //更新
    Integer updateAdmin(Admin admin);

    //根据id获取admin
    Admin getAdminById(Integer adminId);

    Admin getAdminByEmail(String email);

    Admin getAdminByUserName(String username);
}
