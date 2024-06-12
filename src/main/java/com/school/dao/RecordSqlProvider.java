package com.school.dao;

import com.school.entity.Record;
import org.apache.ibatis.jdbc.SQL;

public class RecordSqlProvider {

    public String insertSelective(Record record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("record");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getUsername() != null) {
            sql.VALUES("username", "#{username,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getExamId() != null) {
            sql.VALUES("exam_id", "#{examId,jdbcType=INTEGER}");
        }
        
        if (record.getModelId() != null) {
            sql.VALUES("model_id", "#{modelId,jdbcType=INTEGER}");
        }
        
        if (record.getAnswer() != null) {
            sql.VALUES("answer", "#{answer,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Record record) {
        SQL sql = new SQL();
        sql.UPDATE("record");
        
        if (record.getUsername() != null) {
            sql.SET("username = #{username,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getExamId() != null) {
            sql.SET("exam_id = #{examId,jdbcType=INTEGER}");
        }
        
        if (record.getModelId() != null) {
            sql.SET("model_id = #{modelId,jdbcType=INTEGER}");
        }
        
        if (record.getAnswer() != null) {
            sql.SET("answer = #{answer,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}