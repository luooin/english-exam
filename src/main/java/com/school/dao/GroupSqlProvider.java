package com.school.dao;

import com.school.entity.Group;
import org.apache.ibatis.jdbc.SQL;

public class GroupSqlProvider {

    public String insertSelective(Group record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("group");
        
        if (record.getGroupId() != null) {
            sql.VALUES("group_id", "#{groupId,jdbcType=INTEGER}");
        }
        
        if (record.getGroupName() != null) {
            sql.VALUES("group_name", "#{groupName,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Group record) {
        SQL sql = new SQL();
        sql.UPDATE("group");
        
        if (record.getGroupName() != null) {
            sql.SET("group_name = #{groupName,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("group_id = #{groupId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}