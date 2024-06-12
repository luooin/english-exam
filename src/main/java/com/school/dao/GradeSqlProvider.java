package com.school.dao;

import com.school.entity.Grade;
import org.apache.ibatis.jdbc.SQL;

public class GradeSqlProvider {

    public String insertSelective(Grade record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("grade");
        
        if (record.getGradeId() != null) {
            sql.VALUES("grade_id", "#{gradeId,jdbcType=INTEGER}");
        }
        
        if (record.getUsername() != null) {
            sql.VALUES("username", "#{username,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getGroupName() != null) {
            sql.VALUES("group_name", "#{groupName,jdbcType=VARCHAR}");
        }
        
        if (record.getSpendTime() != null) {
            sql.VALUES("spend_time", "#{spendTime,jdbcType=VARCHAR}");
        }
        
        if (record.getScore() != null) {
            sql.VALUES("score", "#{score,jdbcType=REAL}");
        }
        
        if (record.getExamId() != null) {
            sql.VALUES("exam_id", "#{examId,jdbcType=INTEGER}");
        }
        
        if (record.getFlag() != null) {
            sql.VALUES("flag", "#{flag,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Grade record) {
        SQL sql = new SQL();
        sql.UPDATE("grade");
        
        if (record.getUsername() != null) {
            sql.SET("username = #{username,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }
        
        if (record.getGroupName() != null) {
            sql.SET("group_name = #{groupName,jdbcType=VARCHAR}");
        }
        
        if (record.getSpendTime() != null) {
            sql.SET("spend_time = #{spendTime,jdbcType=VARCHAR}");
        }
        
        if (record.getScore() != null) {
            sql.SET("score = #{score,jdbcType=REAL}");
        }
        
        if (record.getExamId() != null) {
            sql.SET("exam_id = #{examId,jdbcType=INTEGER}");
        }
        
        if (record.getFlag() != null) {
            sql.SET("flag = #{flag,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("grade_id = #{gradeId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}