package com.school.entity;

import com.school.utils.RoleUtil;

public class Role {
    //用作权限验证以及前端显示
    private Integer id;//id
    private String username;//用户名
    private String password;//密码
    private String name;//姓名
    private String email;//邮箱
    private RoleUtil.RoleType type;//角色 学生0 教师1 管理员110

    public Role() {
    }

    //user的role
    public Role(User user, RoleUtil.RoleType type){
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.type = type;
    }
    //admin的role
    public Role(Admin admin, RoleUtil.RoleType type){
        this.id = admin.getAdminId();
        this.name = admin.getAdminName();
        this.username = admin.getAdminUsername();
        this.password = admin.getAdminPassword();
        this.email = admin.getAdminEmail();
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleUtil.RoleType getType() {
        return type;
    }

    public void setType(RoleUtil.RoleType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
