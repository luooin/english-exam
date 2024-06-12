package com.school.dao;

import com.school.entity.Model;
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
public interface ModelMapper {
    @Delete({
        "delete from model",
        "where model_id = #{modelId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer modelId);

    @Insert({
        "insert into model (model_id, title, ",
        "type, content, model_option, ",
        "answer, analysis, ",
        "difficulty, author, ",
        "grade, create_time, ",
        "update_time)",
        "values (#{modelId,jdbcType=INTEGER}, #{title,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=INTEGER}, #{content,jdbcType=VARCHAR}, #{modelOption,jdbcType=VARCHAR}, ",
        "#{answer,jdbcType=VARCHAR}, #{analysis,jdbcType=VARCHAR}, ",
        "#{difficulty,jdbcType=INTEGER}, #{author,jdbcType=VARCHAR}, ",
        "#{grade,jdbcType=REAL}, now(), ",
        "now())"
    })
    int insert(Model record);

    @InsertProvider(type=ModelSqlProvider.class, method="insertSelective")
    int insertSelective(Model record);

    @Select({
        "select",
        "model_id, title, type, content, model_option, answer, analysis, difficulty, ",
        "author, grade, create_time, update_time",
        "from model",
        "where model_id = #{modelId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="model_id", property="modelId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="model_option", property="modelOption", jdbcType=JdbcType.VARCHAR),
        @Result(column="answer", property="answer", jdbcType=JdbcType.VARCHAR),
        @Result(column="analysis", property="analysis", jdbcType=JdbcType.VARCHAR),
        @Result(column="difficulty", property="difficulty", jdbcType=JdbcType.INTEGER),
        @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
        @Result(column="grade", property="grade", jdbcType=JdbcType.REAL),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Model selectByPrimaryKey(Integer modelId);

    @UpdateProvider(type=ModelSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Model record);

    @Update({
        "update model",
        "set title = #{title,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER},",
          "content = #{content,jdbcType=VARCHAR},",
          "model_option = #{modelOption,jdbcType=VARCHAR},",
          "answer = #{answer,jdbcType=VARCHAR},",
          "analysis = #{analysis,jdbcType=VARCHAR},",
          "difficulty = #{difficulty,jdbcType=INTEGER},",
          "author = #{author,jdbcType=VARCHAR},",
          "grade = #{grade,jdbcType=REAL},",
          "update_time = now()",
        "where model_id = #{modelId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Model record);

    @Select({
            "select",
            "model_id, title, type, content, model_option, answer, analysis, difficulty, author, ",
            "grade, create_time, update_time",
            "from model",
            "where type = #{type,jdbcType=INTEGER}",
            "order by update_time desc"
    })
    @Results({
            @Result(column="model_id", property="modelId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
            @Result(column="model_option", property="modelOption", jdbcType=JdbcType.VARCHAR),
            @Result(column="answer", property="answer", jdbcType=JdbcType.VARCHAR),
            @Result(column="analysis", property="analysis", jdbcType=JdbcType.VARCHAR),
            @Result(column="difficulty", property="difficulty", jdbcType=JdbcType.INTEGER),
            @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
            @Result(column="grade", property="grade", jdbcType=JdbcType.REAL),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
        //根据type查询
    List<Model> selectByType(Integer type);

    @Select({
            "select",
            "model_id, title, type, content, model_option, answer, analysis, difficulty, author, ",
            "grade, create_time, update_time",
            "from model",
            "where title like concat(concat('%',#{title}),'%')",
            "order by update_time desc"
    })
    @Results({
            @Result(column="model_id", property="modelId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
            @Result(column="model_option", property="modelOption", jdbcType=JdbcType.VARCHAR),
            @Result(column="answer", property="answer", jdbcType=JdbcType.VARCHAR),
            @Result(column="analysis", property="analysis", jdbcType=JdbcType.VARCHAR),
            @Result(column="difficulty", property="difficulty", jdbcType=JdbcType.INTEGER),
            @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
            @Result(column="grade", property="grade", jdbcType=JdbcType.REAL),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
        //根据title查询
    List<Model> selectByTitle(String title);
    @Select({
            "select",
            "model_id, title, type, content, model_option, answer, analysis, difficulty, author, ",
            "grade, create_time, update_time",
            "from model",
            "order by update_time desc"
    })
    @Results({
            @Result(column="model_id", property="modelId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
            @Result(column="model_option", property="modelOption", jdbcType=JdbcType.VARCHAR),
            @Result(column="answer", property="answer", jdbcType=JdbcType.VARCHAR),
            @Result(column="analysis", property="analysis", jdbcType=JdbcType.VARCHAR),
            @Result(column="difficulty", property="difficulty", jdbcType=JdbcType.INTEGER),
            @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
            @Result(column="grade", property="grade", jdbcType=JdbcType.REAL),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
        //查询所有
    List<Model> selectAll();
    @Select({
            "select count(*) from model"
    })
    Long getModelCount();

    @Select({
            "select",
            "model_id, title, type, content, model_option, answer, analysis, difficulty, author, ",
            "grade, create_time, update_time",
            "from model",
            "where type = #{type,jdbcType=INTEGER}",
            "and title like concat(concat('%',#{title}),'%')",
            "order by update_time desc"
    })
    @Results({
            @Result(column="model_id", property="modelId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
            @Result(column="model_option", property="modelOption", jdbcType=JdbcType.VARCHAR),
            @Result(column="answer", property="answer", jdbcType=JdbcType.VARCHAR),
            @Result(column="analysis", property="analysis", jdbcType=JdbcType.VARCHAR),
            @Result(column="difficulty", property="difficulty", jdbcType=JdbcType.INTEGER),
            @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
            @Result(column="grade", property="grade", jdbcType=JdbcType.REAL),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
        //根据type 和 title查询
    List<Model> selectByTypeAndTitle(Integer type,String title);
}