package com.school.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.dao.UserMapper;
import com.school.entity.User;
import com.school.service.UserService;
import com.school.utils.RoleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User login(String username, String password) {
        return userMapper.login(username, password);
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public Integer register(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User getUserByUserName(String username) {
        return userMapper.getUserByUserName(username);
    }

    @Override
    public Integer deleteUserById(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public PageInfo<User> getAllUserByCondition(Integer currentPage, Integer pageSize,String condition,String info) {
        //设置分页信息保存到threadLocal中
        PageHelper.startPage(currentPage, pageSize);//一定要放在查询之前
        List<User> list = new ArrayList<>();
        if ((info == null || info.equals(""))&&(condition == null || condition.equals(""))){
            //所有条件为空
            list = userMapper.getAllUser();
            PageInfo<User> pageInfo = new PageInfo<>(list);
            return pageInfo;
        }

        if ((info == null || info.equals(""))&&(condition != null || !condition.equals(""))){
            //查询条件为空
            list = userMapper.getAllUser();
            PageInfo<User> pageInfo = new PageInfo<>(list);
            return pageInfo;
        }

        //紧跟着的第一个select方法会被分页，userMapper会被PageInterceptor截拦,
        // 截拦器会从threadLocal中取出分页信息，把分页信息加到sql语句中，实现了分页查旬
        if (condition.equals("0")){
            //姓名
            list = userMapper.getUserListByName(info);
        }else if (condition.equals("1")){
            //学号
            list = userMapper.getUserListByUserName(info);
        }else if (condition.equals("2")){
            //邮箱
            list = userMapper.getUserListByEmail(info);
        }else if (condition.equals("3")){
            //角色
            Integer role = RoleUtil.getRole(info);
            if (role != null){
                list = userMapper.getUserListByRole(role);
            }
        }
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<User> getAllStudentByCondition(Integer currentPage, Integer pageSize, String condition, String info) {
        //设置分页信息保存到threadLocal中
        PageHelper.startPage(currentPage, pageSize);//一定要放在查询之前
        List<User> list = new ArrayList<>();
        if ((info == null || info.equals(""))&&(condition == null || condition.equals(""))){
            //所有条件为空
            list = userMapper.getUserListByRole(RoleUtil.RoleType.STUDENT.getValue());//查询所有学生
            PageInfo<User> pageInfo = new PageInfo<>(list);
            return pageInfo;
        }

        if ((info == null || info.equals(""))&&(condition != null || !condition.equals(""))){
            //查询条件为空
            list = userMapper.getUserListByRole(RoleUtil.RoleType.STUDENT.getValue());//查询所有学生
            PageInfo<User> pageInfo = new PageInfo<>(list);
            return pageInfo;
        }

        //紧跟着的第一个select方法会被分页，userMapper会被PageInterceptor截拦,
        // 截拦器会从threadLocal中取出分页信息，把分页信息加到sql语句中，实现了分页查旬
        if (condition.equals("0")){
            //姓名
            list = userMapper.getStudentListByName(RoleUtil.RoleType.STUDENT.getValue(), info);
        }else if (condition.equals("1")){
            //学号
            list = userMapper.getStudentListByUserName(RoleUtil.RoleType.STUDENT.getValue(), info);
        }else if (condition.equals("2")){
            //邮箱
            list = userMapper.getStudentListByEmail(RoleUtil.RoleType.STUDENT.getValue(),info);
        }
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }
}
