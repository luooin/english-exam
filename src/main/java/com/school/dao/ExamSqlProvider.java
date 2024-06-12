package com.school.dao;

import com.school.entity.Exam;
import org.apache.ibatis.jdbc.SQL;

public class ExamSqlProvider {

    public String insertSelective(Exam record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("exam");
        
        if (record.getExamId() != null) {
            sql.VALUES("exam_id", "#{examId,jdbcType=INTEGER}");
        }
        
        if (record.getExamName() != null) {
            sql.VALUES("exam_name", "#{examName,jdbcType=VARCHAR}");
        }
        
        if (record.getExamContent() != null) {
            sql.VALUES("exam_content", "#{examContent,jdbcType=VARCHAR}");
        }
        
        if (record.getExamType() != null) {
            sql.VALUES("exam_type", "#{examType,jdbcType=INTEGER}");
        }
        
        if (record.getNeedTime() != null) {
            sql.VALUES("need_time", "#{needTime,jdbcType=VARCHAR}");
        }
        
        if (record.getExamAuthor() != null) {
            sql.VALUES("exam_author", "#{examAuthor,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getExamAnswer() != null) {
            sql.VALUES("exam_answer", "#{examAnswer,jdbcType=VARCHAR}");
        }
        
        if (record.getExamAnalysis() != null) {
            sql.VALUES("exam_analysis", "#{examAnalysis,jdbcType=VARCHAR}");
        }
        
        if (record.getExamGrade() != null) {
            sql.VALUES("exam_grade", "#{examGrade,jdbcType=REAL}");
        }
        
        if (record.getModelIds() != null) {
            sql.VALUES("model_ids", "#{modelIds,jdbcType=VARCHAR}");
        }
        
        if (record.getExamFlag() != null) {
            sql.VALUES("exam_flag", "#{examFlag,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Exam record) {
        SQL sql = new SQL();
        sql.UPDATE("exam");
        
        if (record.getExamName() != null) {
            sql.SET("exam_name = #{examName,jdbcType=VARCHAR}");
        }
        
        if (record.getExamContent() != null) {
            sql.SET("exam_content = #{examContent,jdbcType=VARCHAR}");
        }
        
        if (record.getExamType() != null) {
            sql.SET("exam_type = #{examType,jdbcType=INTEGER}");
        }
        
        if (record.getNeedTime() != null) {
            sql.SET("need_time = #{needTime,jdbcType=VARCHAR}");
        }
        
        if (record.getExamAuthor() != null) {
            sql.SET("exam_author = #{examAuthor,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getExamAnswer() != null) {
            sql.SET("exam_answer = #{examAnswer,jdbcType=VARCHAR}");
        }
        
        if (record.getExamAnalysis() != null) {
            sql.SET("exam_analysis = #{examAnalysis,jdbcType=VARCHAR}");
        }
        
        if (record.getExamGrade() != null) {
            sql.SET("exam_grade = #{examGrade,jdbcType=REAL}");
        }
        
        if (record.getModelIds() != null) {
            sql.SET("model_ids = #{modelIds,jdbcType=VARCHAR}");
        }
        
        if (record.getExamFlag() != null) {
            sql.SET("exam_flag = #{examFlag,jdbcType=INTEGER}");
        }
        
        sql.WHERE("exam_id = #{examId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}