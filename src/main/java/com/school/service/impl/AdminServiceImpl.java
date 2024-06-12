package com.school.service.impl;

import com.school.dao.AdminMapper;
import com.school.entity.Admin;
import com.school.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Admin login(String adminUserName, String adminPassword) {
        return adminMapper.login(adminUserName, adminPassword);
    }

    @Override
    public Integer updateAdmin(Admin admin) {
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public Admin getAdminById(Integer adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public Admin getAdminByEmail(String email) {
        return adminMapper.getAdminByEmail(email);
    }

    @Override
    public Admin getAdminByUserName(String username) {
        return adminMapper.getAdminByUserName(username);
    }
}
