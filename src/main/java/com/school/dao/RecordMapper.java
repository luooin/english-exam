package com.school.dao;

import com.school.entity.Record;
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
public interface RecordMapper {
    @Delete({
        "delete from record",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into record (id, username, ",
        "name, exam_id, model_id, ",
        "answer, create_time, ",
        "update_time)",
        "values (#{id,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{examId,jdbcType=INTEGER}, #{modelId,jdbcType=INTEGER}, ",
        "#{answer,jdbcType=VARCHAR}, now(), ",
        "now())"
    })
    int insert(Record record);

    @InsertProvider(type=RecordSqlProvider.class, method="insertSelective")
    int insertSelective(Record record);

    @Select({
        "select",
        "id, username, name, exam_id, model_id, answer, create_time, update_time",
        "from record",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="exam_id", property="examId", jdbcType=JdbcType.INTEGER),
        @Result(column="model_id", property="modelId", jdbcType=JdbcType.INTEGER),
        @Result(column="answer", property="answer", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Record selectByPrimaryKey(Integer id);

    @UpdateProvider(type=RecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Record record);

    @Update({
        "update record",
        "set username = #{username,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "exam_id = #{examId,jdbcType=INTEGER},",
          "model_id = #{modelId,jdbcType=INTEGER},",
          "answer = #{answer,jdbcType=VARCHAR},",
          "update_time = now()",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Record record);


    @Select({
            "select",
            "id, username, name, exam_id, model_id, answer, create_time, update_time",
            "from record",
            "where username = #{username,jdbcType=VARCHAR}",
            "and exam_id = #{exam_id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="exam_id", property="examId", jdbcType=JdbcType.INTEGER),
            @Result(column="model_id", property="modelId", jdbcType=JdbcType.INTEGER),
            @Result(column="answer", property="answer", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Record> getRecordList(String username, Integer exam_id);
}