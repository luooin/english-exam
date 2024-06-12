package com.school.dao;

import com.school.entity.Group;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface GroupMapper {
    @Delete({
        "delete from group",
        "where group_id = #{groupId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer groupId);

    @Insert({
        "insert into group (group_id, group_name)",
        "values (#{groupId,jdbcType=INTEGER}, #{groupName,jdbcType=VARCHAR})"
    })
    int insert(Group record);

    @InsertProvider(type=GroupSqlProvider.class, method="insertSelective")
    int insertSelective(Group record);

    @Select({
        "select",
        "group_id, group_name",
        "from group",
        "where group_id = #{groupId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="group_id", property="groupId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR)
    })
    Group selectByPrimaryKey(Integer groupId);

    @UpdateProvider(type=GroupSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Group record);

    @Update({
        "update group",
        "set group_name = #{groupName,jdbcType=VARCHAR}",
        "where group_id = #{groupId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Group record);
}