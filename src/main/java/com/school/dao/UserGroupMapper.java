package com.school.dao;

import com.school.entity.UserGroup;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface UserGroupMapper {
    @Delete({
        "delete from user_group",
        "where user_group_id = #{userGroupId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userGroupId);

    @Insert({
        "insert into user_group (user_group_id, username, ",
        "group_id)",
        "values (#{userGroupId,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, ",
        "#{groupId,jdbcType=INTEGER})"
    })
    int insert(UserGroup record);

    @InsertProvider(type=UserGroupSqlProvider.class, method="insertSelective")
    int insertSelective(UserGroup record);

    @Select({
        "select",
        "user_group_id, username, group_id",
        "from user_group",
        "where user_group_id = #{userGroupId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="user_group_id", property="userGroupId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="group_id", property="groupId", jdbcType=JdbcType.INTEGER)
    })
    UserGroup selectByPrimaryKey(Integer userGroupId);

    @UpdateProvider(type=UserGroupSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserGroup record);

    @Update({
        "update user_group",
        "set username = #{username,jdbcType=VARCHAR},",
          "group_id = #{groupId,jdbcType=INTEGER}",
        "where user_group_id = #{userGroupId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserGroup record);
}