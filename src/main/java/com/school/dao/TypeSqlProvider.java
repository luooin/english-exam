package com.school.dao;

import com.school.entity.Type;
import org.apache.ibatis.jdbc.SQL;

public class TypeSqlProvider {

    public String insertSelective(Type record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("type");
        
        if (record.getTypeId() != null) {
            sql.VALUES("type_id", "#{typeId,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Type record) {
        SQL sql = new SQL();
        sql.UPDATE("type");
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("type_id = #{typeId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}