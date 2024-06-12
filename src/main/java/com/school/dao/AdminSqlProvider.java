package com.school.dao;

import com.school.entity.Admin;
import org.apache.ibatis.jdbc.SQL;

public class AdminSqlProvider {

    public String insertSelective(Admin record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("admin");
        
        if (record.getAdminId() != null) {
            sql.VALUES("admin_id", "#{adminId,jdbcType=INTEGER}");
        }
        
        if (record.getAdminUsername() != null) {
            sql.VALUES("admin_username", "#{adminUsername,jdbcType=VARCHAR}");
        }
        
        if (record.getAdminName() != null) {
            sql.VALUES("admin_name", "#{adminName,jdbcType=VARCHAR}");
        }
        
        if (record.getAdminPassword() != null) {
            sql.VALUES("admin_password", "#{adminPassword,jdbcType=VARCHAR}");
        }
        
        if (record.getAdminEmail() != null) {
            sql.VALUES("admin_email", "#{adminEmail,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Admin record) {
        SQL sql = new SQL();
        sql.UPDATE("admin");
        
        if (record.getAdminUsername() != null) {
            sql.SET("admin_username = #{adminUsername,jdbcType=VARCHAR}");
        }
        
        if (record.getAdminName() != null) {
            sql.SET("admin_name = #{adminName,jdbcType=VARCHAR}");
        }
        
        if (record.getAdminPassword() != null) {
            sql.SET("admin_password = #{adminPassword,jdbcType=VARCHAR}");
        }
        
        if (record.getAdminEmail() != null) {
            sql.SET("admin_email = #{adminEmail,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("admin_id = #{adminId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}