package com.school.dao;

import com.school.entity.Admin;
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
public interface AdminMapper {
    @Delete({
        "delete from admin",
        "where admin_id = #{adminId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer adminId);

    @Insert({
        "insert into admin (admin_id, admin_username, ",
        "admin_name, admin_password, ",
        "admin_email, create_time, ",
        "update_time)",
        "values (#{adminId,jdbcType=INTEGER}, #{adminUsername,jdbcType=VARCHAR}, ",
        "#{adminName,jdbcType=VARCHAR}, #{adminPassword,jdbcType=VARCHAR}, ",
        "#{adminEmail,jdbcType=VARCHAR}, now(), ",
        "now())"
    })
    int insert(Admin record);

    @InsertProvider(type=AdminSqlProvider.class, method="insertSelective")
    int insertSelective(Admin record);

    @Select({
        "select",
        "admin_id, admin_username, admin_name, admin_password, admin_email, create_time, ",
        "update_time",
        "from admin",
        "where admin_id = #{adminId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="admin_id", property="adminId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="admin_username", property="adminUsername", jdbcType=JdbcType.VARCHAR),
        @Result(column="admin_name", property="adminName", jdbcType=JdbcType.VARCHAR),
        @Result(column="admin_password", property="adminPassword", jdbcType=JdbcType.VARCHAR),
        @Result(column="admin_email", property="adminEmail", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Admin selectByPrimaryKey(Integer adminId);

    @UpdateProvider(type=AdminSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Admin record);

    @Update({
        "update admin",
        "set admin_username = #{adminUsername,jdbcType=VARCHAR},",
          "admin_name = #{adminName,jdbcType=VARCHAR},",
          "admin_password = #{adminPassword,jdbcType=VARCHAR},",
          "admin_email = #{adminEmail,jdbcType=VARCHAR},",
          "update_time = now()",
        "where admin_id = #{adminId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Admin record);

    @Select({
            "select",
            "admin_id, admin_username, admin_name, admin_password, admin_email, create_time, ",
            "update_time",
            "from admin",
            "where admin_username = #{admin_username,jdbcType=VARCHAR}",
            "and admin_password = #{admin_password,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="admin_id", property="adminId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="admin_username", property="adminUsername", jdbcType=JdbcType.VARCHAR),
            @Result(column="admin_name", property="adminName", jdbcType=JdbcType.VARCHAR),
            @Result(column="admin_password", property="adminPassword", jdbcType=JdbcType.VARCHAR),
            @Result(column="admin_email", property="adminEmail", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Admin login(String admin_username,String admin_password);

    @Select({
            "select",
            "admin_id, admin_username, admin_name, admin_password, admin_email, create_time, ",
            "update_time",
            "from admin",
            "where admin_email = #{admin_email,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="admin_id", property="adminId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="admin_username", property="adminUsername", jdbcType=JdbcType.VARCHAR),
            @Result(column="admin_name", property="adminName", jdbcType=JdbcType.VARCHAR),
            @Result(column="admin_password", property="adminPassword", jdbcType=JdbcType.VARCHAR),
            @Result(column="admin_email", property="adminEmail", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Admin getAdminByEmail(String admin_email);

    @Select({
            "select",
            "admin_id, admin_username, admin_name, admin_password, admin_email, create_time, ",
            "update_time",
            "from admin",
            "where admin_username = #{admin_username,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="admin_id", property="adminId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="admin_username", property="adminUsername", jdbcType=JdbcType.VARCHAR),
            @Result(column="admin_name", property="adminName", jdbcType=JdbcType.VARCHAR),
            @Result(column="admin_password", property="adminPassword", jdbcType=JdbcType.VARCHAR),
            @Result(column="admin_email", property="adminEmail", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Admin getAdminByUserName(String admin_username);
}