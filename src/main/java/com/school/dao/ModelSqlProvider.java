package com.school.dao;

import com.school.entity.Model;
import org.apache.ibatis.jdbc.SQL;

public class ModelSqlProvider {

    public String insertSelective(Model record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("model");
        
        if (record.getModelId() != null) {
            sql.VALUES("model_id", "#{modelId,jdbcType=INTEGER}");
        }
        
        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=INTEGER}");
        }
        
        if (record.getContent() != null) {
            sql.VALUES("content", "#{content,jdbcType=VARCHAR}");
        }
        
        if (record.getModelOption() != null) {
            sql.VALUES("model_option", "#{modelOption,jdbcType=VARCHAR}");
        }
        
        if (record.getAnswer() != null) {
            sql.VALUES("answer", "#{answer,jdbcType=VARCHAR}");
        }
        
        if (record.getAnalysis() != null) {
            sql.VALUES("analysis", "#{analysis,jdbcType=VARCHAR}");
        }
        
        if (record.getDifficulty() != null) {
            sql.VALUES("difficulty", "#{difficulty,jdbcType=INTEGER}");
        }
        
        if (record.getAuthor() != null) {
            sql.VALUES("author", "#{author,jdbcType=VARCHAR}");
        }
        
        if (record.getGrade() != null) {
            sql.VALUES("grade", "#{grade,jdbcType=REAL}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Model record) {
        SQL sql = new SQL();
        sql.UPDATE("model");
        
        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=INTEGER}");
        }
        
        if (record.getContent() != null) {
            sql.SET("content = #{content,jdbcType=VARCHAR}");
        }
        
        if (record.getModelOption() != null) {
            sql.SET("model_option = #{modelOption,jdbcType=VARCHAR}");
        }
        
        if (record.getAnswer() != null) {
            sql.SET("answer = #{answer,jdbcType=VARCHAR}");
        }
        
        if (record.getAnalysis() != null) {
            sql.SET("analysis = #{analysis,jdbcType=VARCHAR}");
        }
        
        if (record.getDifficulty() != null) {
            sql.SET("difficulty = #{difficulty,jdbcType=INTEGER}");
        }
        
        if (record.getAuthor() != null) {
            sql.SET("author = #{author,jdbcType=VARCHAR}");
        }
        
        if (record.getGrade() != null) {
            sql.SET("grade = #{grade,jdbcType=REAL}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("model_id = #{modelId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}