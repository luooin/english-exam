package com.school.dao;

import com.school.entity.Grade;
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
public interface GradeMapper {
    @Delete({
        "delete from grade",
        "where grade_id = #{gradeId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer gradeId);

    @Insert({
        "insert into grade (grade_id, username, ",
        "name, title, group_name, ",
        "spend_time, score, exam_id, ",
        "flag, create_time, ",
        "update_time)",
        "values (#{gradeId,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, #{groupName,jdbcType=VARCHAR}, ",
        "#{spendTime,jdbcType=VARCHAR}, #{score,jdbcType=REAL}, #{examId,jdbcType=INTEGER}, ",
        "#{flag,jdbcType=INTEGER}, now(), ",
        "now())"
    })
    int insert(Grade record);

    @InsertProvider(type=GradeSqlProvider.class, method="insertSelective")
    int insertSelective(Grade record);

    @Select({
        "select",
        "grade_id, username, name, title, group_name, spend_time, score, exam_id, flag, ",
        "create_time, update_time",
        "from grade",
        "where grade_id = #{gradeId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="grade_id", property="gradeId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
        @Result(column="spend_time", property="spendTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="score", property="score", jdbcType=JdbcType.REAL),
        @Result(column="exam_id", property="examId", jdbcType=JdbcType.INTEGER),
        @Result(column="flag", property="flag", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Grade selectByPrimaryKey(Integer gradeId);

    @UpdateProvider(type=GradeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Grade record);

    @Update({
        "update grade",
        "set username = #{username,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "group_name = #{groupName,jdbcType=VARCHAR},",
          "spend_time = #{spendTime,jdbcType=VARCHAR},",
          "score = #{score,jdbcType=REAL},",
          "exam_id = #{examId,jdbcType=INTEGER},",
          "flag = #{flag,jdbcType=INTEGER},",
          "update_time = now()",
        "where grade_id = #{gradeId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Grade record);

    @Select({
            "select",
            "grade_id, username, name, title, group_name, spend_time, score, exam_id, flag, ",
            "create_time, update_time",
            "from grade",
            "where username = #{username,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="grade_id", property="gradeId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
            @Result(column="spend_time", property="spendTime", jdbcType=JdbcType.VARCHAR),
            @Result(column="score", property="score", jdbcType=JdbcType.REAL),
            @Result(column="exam_id", property="examId", jdbcType=JdbcType.INTEGER),
            @Result(column="flag", property="flag", jdbcType=JdbcType.INTEGER),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    //查找用户的考试记录
    List<Grade> getGradeByUserName(String username);

    @Select({
            "select",
            "grade_id, username, name, title, group_name, spend_time, score, exam_id, flag, ",
            "create_time, update_time",
            "from grade",
            "where username = #{username,jdbcType=VARCHAR}",
            "and exam_id = #{exam_id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="grade_id", property="gradeId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
            @Result(column="spend_time", property="spendTime", jdbcType=JdbcType.VARCHAR),
            @Result(column="score", property="score", jdbcType=JdbcType.REAL),
            @Result(column="exam_id", property="examId", jdbcType=JdbcType.INTEGER),
            @Result(column="flag", property="flag", jdbcType=JdbcType.INTEGER),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Grade getGradeByExamId(String username,Integer exam_id);

    @Select({
            "select",
            "grade_id, username, name, title, group_name, spend_time, score, exam_id, flag, ",
            "create_time, update_time",
            "from grade",
            "where exam_id = #{exam_id,jdbcType=INTEGER}",
            "order by score desc"
    })
    @Results({
            @Result(column="grade_id", property="gradeId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
            @Result(column="spend_time", property="spendTime", jdbcType=JdbcType.VARCHAR),
            @Result(column="score", property="score", jdbcType=JdbcType.REAL),
            @Result(column="exam_id", property="examId", jdbcType=JdbcType.INTEGER),
            @Result(column="flag", property="flag", jdbcType=JdbcType.INTEGER),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Grade> getGradesByExamId(Integer exam_id);


    @Select({
            "select",
            "grade_id, username, name, title, group_name, spend_time, score, exam_id, flag, ",
            "create_time, update_time",
            "from grade"
    })
    @Results({
            @Result(column="grade_id", property="gradeId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
            @Result(column="spend_time", property="spendTime", jdbcType=JdbcType.VARCHAR),
            @Result(column="score", property="score", jdbcType=JdbcType.REAL),
            @Result(column="exam_id", property="examId", jdbcType=JdbcType.INTEGER),
            @Result(column="flag", property="flag", jdbcType=JdbcType.INTEGER),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Grade> getAllGrade();

    @Select({
            "select",
            "grade_id, username, name, title, group_name, spend_time, score, exam_id, flag, ",
            "create_time, update_time",
            "from grade",
            "where title like concat(concat('%',#{title}),'%')",
            "order by update_time desc"
    })
    @Results({
            @Result(column="grade_id", property="gradeId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
            @Result(column="spend_time", property="spendTime", jdbcType=JdbcType.VARCHAR),
            @Result(column="score", property="score", jdbcType=JdbcType.REAL),
            @Result(column="exam_id", property="examId", jdbcType=JdbcType.INTEGER),
            @Result(column="flag", property="flag", jdbcType=JdbcType.INTEGER),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Grade> getGradeByTitle(String title);

    @Select({
            "select",
            "grade_id, username, name, title, group_name, spend_time, score, exam_id, flag, ",
            "create_time, update_time",
            "from grade",
            "where username = #{username,jdbcType=VARCHAR}",
            "and title like concat(concat('%',#{title}),'%')",
            "order by update_time desc"
    })
    @Results({
            @Result(column="grade_id", property="gradeId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
            @Result(column="spend_time", property="spendTime", jdbcType=JdbcType.VARCHAR),
            @Result(column="score", property="score", jdbcType=JdbcType.REAL),
            @Result(column="exam_id", property="examId", jdbcType=JdbcType.INTEGER),
            @Result(column="flag", property="flag", jdbcType=JdbcType.INTEGER),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Grade> getGradeByUserNameAndTitle(String username,String title);

    @Select({
            "select",
            "grade_id, username, name, title, group_name, spend_time, score, exam_id, flag, ",
            "create_time, update_time",
            "from grade",
            "where group_name like concat(concat('%',#{group_name}),'%')",
            "order by update_time desc"
    })
    @Results({
            @Result(column="grade_id", property="gradeId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
            @Result(column="spend_time", property="spendTime", jdbcType=JdbcType.VARCHAR),
            @Result(column="score", property="score", jdbcType=JdbcType.REAL),
            @Result(column="exam_id", property="examId", jdbcType=JdbcType.INTEGER),
            @Result(column="flag", property="flag", jdbcType=JdbcType.INTEGER),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Grade> getGradeListByGroupName(String group_name);

    @Select({
            "select",
            "grade_id, username, name, title, group_name, spend_time, score, exam_id, flag, ",
            "create_time, update_time",
            "from grade",
            "where exam_id = #{exam_id,jdbcType=INTEGER}",
            "and group_name like concat(concat('%',#{group_name}),'%')",
            "order by score desc"
    })
    @Results({
            @Result(column="grade_id", property="gradeId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
            @Result(column="spend_time", property="spendTime", jdbcType=JdbcType.VARCHAR),
            @Result(column="score", property="score", jdbcType=JdbcType.REAL),
            @Result(column="exam_id", property="examId", jdbcType=JdbcType.INTEGER),
            @Result(column="flag", property="flag", jdbcType=JdbcType.INTEGER),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Grade> getGradeForChartByGroupName(Integer exam_id,String group_name);

    @Select({
            "select",
            "grade_id, username, name, title, group_name, spend_time, score, exam_id, flag, ",
            "create_time, update_time",
            "from grade",
            "where username like concat(concat('%',#{username}),'%')",
            "order by update_time desc"
    })
    @Results({
            @Result(column="grade_id", property="gradeId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
            @Result(column="spend_time", property="spendTime", jdbcType=JdbcType.VARCHAR),
            @Result(column="score", property="score", jdbcType=JdbcType.REAL),
            @Result(column="exam_id", property="examId", jdbcType=JdbcType.INTEGER),
            @Result(column="flag", property="flag", jdbcType=JdbcType.INTEGER),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    //模糊查找
    List<Grade> getGradeListByUserName(String username);

    @Select({
            "select",
            "grade_id, username, name, title, group_name, spend_time, score, exam_id, flag, ",
            "create_time, update_time",
            "from grade",
            "where exam_id = #{exam_id,jdbcType=INTEGER}",
            "and username like concat(concat('%',#{username}),'%')",
            "order by score desc"
    })
    @Results({
            @Result(column="grade_id", property="gradeId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
            @Result(column="spend_time", property="spendTime", jdbcType=JdbcType.VARCHAR),
            @Result(column="score", property="score", jdbcType=JdbcType.REAL),
            @Result(column="exam_id", property="examId", jdbcType=JdbcType.INTEGER),
            @Result(column="flag", property="flag", jdbcType=JdbcType.INTEGER),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
        //模糊查找
    List<Grade> getGradeForChartByUserName(Integer exam_id,String username);

    @Select({
            "select",
            "grade_id, username, name, title, group_name, spend_time, score, exam_id, flag, ",
            "create_time, update_time",
            "from grade",
            "where title like concat(concat('%',#{title}),'%')",
            "order by update_time desc"
    })
    @Results({
            @Result(column="grade_id", property="gradeId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
            @Result(column="spend_time", property="spendTime", jdbcType=JdbcType.VARCHAR),
            @Result(column="score", property="score", jdbcType=JdbcType.REAL),
            @Result(column="exam_id", property="examId", jdbcType=JdbcType.INTEGER),
            @Result(column="flag", property="flag", jdbcType=JdbcType.INTEGER),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Grade> getGradeListByTitle(String title);

    @Select({
            "select",
            "grade_id, username, name, title, group_name, spend_time, score, exam_id, flag, ",
            "create_time, update_time",
            "from grade",
            "where name like concat(concat('%',#{name}),'%')",
            "order by update_time desc"
    })
    @Results({
            @Result(column="grade_id", property="gradeId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
            @Result(column="spend_time", property="spendTime", jdbcType=JdbcType.VARCHAR),
            @Result(column="score", property="score", jdbcType=JdbcType.REAL),
            @Result(column="exam_id", property="examId", jdbcType=JdbcType.INTEGER),
            @Result(column="flag", property="flag", jdbcType=JdbcType.INTEGER),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Grade> getGradeListByName(String name);

    @Select({
            "select",
            "grade_id, username, name, title, group_name, spend_time, score, exam_id, flag, ",
            "create_time, update_time",
            "from grade",
            "where exam_id = #{exam_id,jdbcType=INTEGER}",
            "and name like concat(concat('%',#{name}),'%')",
            "order by score desc"
    })
    @Results({
            @Result(column="grade_id", property="gradeId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
            @Result(column="spend_time", property="spendTime", jdbcType=JdbcType.VARCHAR),
            @Result(column="score", property="score", jdbcType=JdbcType.REAL),
            @Result(column="exam_id", property="examId", jdbcType=JdbcType.INTEGER),
            @Result(column="flag", property="flag", jdbcType=JdbcType.INTEGER),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Grade> getGradeForChartByName(Integer exam_id,String name);
}