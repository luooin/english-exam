package com.school.service;

import com.github.pagehelper.PageInfo;
import com.school.entity.User;

import java.util.List;

public interface UserService {
    User getUserById(Integer id);
    //登录
    User login(String username,String password);

    List<User> getAllUser();

    Integer register(User user);

    User getUserByUserName(String username);

    Integer deleteUserById(Integer id);

    Integer updateUser(User user);

    //根据条件查询
    PageInfo<User> getAllUserByCondition(Integer currentPage, Integer pageSize,String condition,String info);

    //根据条件查询student
    PageInfo<User> getAllStudentByCondition(Integer currentPage, Integer pageSize,String condition,String info);

    User getUserByEmail(String email);
}
