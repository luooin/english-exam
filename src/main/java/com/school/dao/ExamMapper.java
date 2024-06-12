package com.school.dao;

import com.school.entity.Exam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamMapper {
    @Delete({
        "delete from exam",
        "where exam_id = #{examId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer examId);

    @Insert({
        "insert into exam (exam_id, exam_name, ",
        "exam_content, exam_type, ",
        "need_time, exam_author, ",
        "create_time, update_time, ",
        "exam_answer, exam_analysis, ",
        "exam_grade, model_ids, ",
        "exam_flag)",
        "values (#{examId,jdbcType=INTEGER}, #{examName,jdbcType=VARCHAR}, ",
        "#{examContent,jdbcType=VARCHAR}, #{examType,jdbcType=INTEGER}, ",
        "#{needTime,jdbcType=VARCHAR}, #{examAuthor,jdbcType=VARCHAR}, ",
        "now(), now(), ",
        "#{examAnswer,jdbcType=VARCHAR}, #{examAnalysis,jdbcType=VARCHAR}, ",
        "#{examGrade,jdbcType=REAL}, #{modelIds,jdbcType=VARCHAR}, ",
        "#{examFlag,jdbcType=INTEGER})"
    })
    int insert(Exam record);

    @InsertProvider(type=ExamSqlProvider.class, method="insertSelective")
    int insertSelective(Exam record);

    @Select({
        "select",
        "exam_id, exam_name, exam_content, exam_type, need_time, exam_author, create_time, ",
        "update_time, exam_answer, exam_analysis, exam_grade, model_ids, exam_flag",
        "from exam",
        "where exam_id = #{examId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="exam_id", property="examId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="exam_name", property="examName", jdbcType=JdbcType.VARCHAR),
        @Result(column="exam_content", property="examContent", jdbcType=JdbcType.VARCHAR),
        @Result(column="exam_type", property="examType", jdbcType=JdbcType.INTEGER),
        @Result(column="need_time", property="needTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="exam_author", property="examAuthor", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="exam_answer", property="examAnswer", jdbcType=JdbcType.VARCHAR),
        @Result(column="exam_analysis", property="examAnalysis", jdbcType=JdbcType.VARCHAR),
        @Result(column="exam_grade", property="examGrade", jdbcType=JdbcType.REAL),
        @Result(column="model_ids", property="modelIds", jdbcType=JdbcType.VARCHAR),
        @Result(column="exam_flag", property="examFlag", jdbcType=JdbcType.INTEGER)
    })
    Exam selectByPrimaryKey(Integer examId);

    @UpdateProvider(type=ExamSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Exam record);

    @Update({
        "update exam",
        "set exam_name = #{examName,jdbcType=VARCHAR},",
          "exam_content = #{examContent,jdbcType=VARCHAR},",
          "exam_type = #{examType,jdbcType=INTEGER},",
          "need_time = #{needTime,jdbcType=VARCHAR},",
          "exam_author = #{examAuthor,jdbcType=VARCHAR},",
          "update_time = now(),",
          "exam_answer = #{examAnswer,jdbcType=VARCHAR},",
          "exam_analysis = #{examAnalysis,jdbcType=VARCHAR},",
          "exam_grade = #{examGrade,jdbcType=REAL},",
          "model_ids = #{modelIds,jdbcType=VARCHAR},",
          "exam_flag = #{examFlag,jdbcType=INTEGER}",
        "where exam_id = #{examId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Exam record);

    @Select({
            "select",
            "exam_id, exam_name, exam_content, exam_type, need_time, exam_author, create_time, ",
            "update_time, exam_answer, exam_analysis, exam_grade, model_ids, exam_flag",
            "from exam",
            "order by update_time desc"
    })
    @Results({
            @Result(column="exam_id", property="examId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="exam_name", property="examName", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_content", property="examContent", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_type", property="examType", jdbcType=JdbcType.INTEGER),
            @Result(column="need_time", property="needTime", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_author", property="examAuthor", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="exam_answer", property="examAnswer", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_analysis", property="examAnalysis", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_grade", property="examGrade", jdbcType=JdbcType.REAL),
            @Result(column="model_ids", property="modelIds", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_flag", property="examFlag", jdbcType=JdbcType.INTEGER)
    })
    List<Exam> getAllExam();

    @Select({
            "select",
            "exam_id, exam_name, exam_content, exam_type, need_time, exam_author, create_time, ",
            "update_time, exam_answer, exam_analysis, exam_grade, model_ids, exam_flag",
            "from exam",
            "where exam_flag = #{examFlag,jdbcType=INTEGER}",
            "order by update_time desc"
    })
    @Results({
            @Result(column="exam_id", property="examId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="exam_name", property="examName", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_content", property="examContent", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_type", property="examType", jdbcType=JdbcType.INTEGER),
            @Result(column="need_time", property="needTime", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_author", property="examAuthor", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="exam_answer", property="examAnswer", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_analysis", property="examAnalysis", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_grade", property="examGrade", jdbcType=JdbcType.REAL),
            @Result(column="model_ids", property="modelIds", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_flag", property="examFlag", jdbcType=JdbcType.INTEGER)
    })
    List<Exam> getExamListByExamFlag(Integer exam_flag);

    @Select({
            "select",
            "exam_id, exam_name, exam_content, exam_type, need_time, exam_author, create_time, ",
            "update_time, exam_answer, exam_analysis, exam_grade, model_ids, exam_flag",
            "from exam",
            "where exam_name like concat(concat('%',#{exam_name}),'%')",
            "order by update_time desc"
    })
    @Results({
            @Result(column="exam_id", property="examId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="exam_name", property="examName", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_content", property="examContent", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_type", property="examType", jdbcType=JdbcType.INTEGER),
            @Result(column="need_time", property="needTime", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_author", property="examAuthor", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="exam_answer", property="examAnswer", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_analysis", property="examAnalysis", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_grade", property="examGrade", jdbcType=JdbcType.REAL),
            @Result(column="model_ids", property="modelIds", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_flag", property="examFlag", jdbcType=JdbcType.INTEGER)
    })
    List<Exam> getExamByCondition(String exam_name);

    @Select({
            "select",
            "exam_id, exam_name, exam_content, exam_type, need_time, exam_author, create_time, ",
            "update_time, exam_answer, exam_analysis, exam_grade, model_ids, exam_flag",
            "from exam",
            "where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(update_time)",
            "order by update_time desc"
    })
    @Results({
            @Result(column="exam_id", property="examId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="exam_name", property="examName", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_content", property="examContent", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_type", property="examType", jdbcType=JdbcType.INTEGER),
            @Result(column="need_time", property="needTime", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_author", property="examAuthor", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="exam_answer", property="examAnswer", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_analysis", property="examAnalysis", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_grade", property="examGrade", jdbcType=JdbcType.REAL),
            @Result(column="model_ids", property="modelIds", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_flag", property="examFlag", jdbcType=JdbcType.INTEGER)
    })
    List<Exam> getExamRecently();

    @Select({
            "select count(*) from exam"
    })

    Long getExamCount();
}