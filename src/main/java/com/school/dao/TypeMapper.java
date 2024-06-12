package com.school.dao;

import com.school.entity.Type;
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

@Repository
public interface TypeMapper {
    @Delete({
        "delete from type",
        "where type_id = #{typeId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer typeId);

    @Insert({
        "insert into type (type_id, type)",
        "values (#{typeId,jdbcType=INTEGER}, #{type,jdbcType=VARCHAR})"
    })
    int insert(Type record);

    @InsertProvider(type=TypeSqlProvider.class, method="insertSelective")
    int insertSelective(Type record);

    @Select({
        "select",
        "type_id, type",
        "from type",
        "where type_id = #{typeId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR)
    })
    Type selectByPrimaryKey(Integer typeId);

    @UpdateProvider(type=TypeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Type record);

    @Update({
        "update type",
        "set type = #{type,jdbcType=VARCHAR}",
        "where type_id = #{typeId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Type record);
}