package com.school.dao;

import com.school.entity.UserGroup;
import org.apache.ibatis.jdbc.SQL;

public class UserGroupSqlProvider {

    public String insertSelective(UserGroup record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("user_group");
        
        if (record.getUserGroupId() != null) {
            sql.VALUES("user_group_id", "#{userGroupId,jdbcType=INTEGER}");
        }
        
        if (record.getUsername() != null) {
            sql.VALUES("username", "#{username,jdbcType=VARCHAR}");
        }
        
        if (record.getGroupId() != null) {
            sql.VALUES("group_id", "#{groupId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(UserGroup record) {
        SQL sql = new SQL();
        sql.UPDATE("user_group");
        
        if (record.getUsername() != null) {
            sql.SET("username = #{username,jdbcType=VARCHAR}");
        }
        
        if (record.getGroupId() != null) {
            sql.SET("group_id = #{groupId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("user_group_id = #{userGroupId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}