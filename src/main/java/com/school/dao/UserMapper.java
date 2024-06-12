package com.school.dao;

import com.school.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    @Delete({
        "delete from user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into user (id, username,",
        "name, password,",
        "email, role, createtime,",
        "updatetime)",
        "values (#{id,jdbcType=INTEGER},#{username,jdbcType=VARCHAR},",
        "#{name,jdbcType=VARCHAR},#{password,jdbcType=VARCHAR},",
        "#{email,jdbcType=VARCHAR},#{role,jdbcType=INTEGER},now(),now())"
    })
    int insert(User record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    int insertSelective(User record);

    @Select({
        "select",
        "id, username, name, password, email, role, createtime, updatetime",
        "from user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="role", property="role", jdbcType=JdbcType.INTEGER),
        @Result(column="updatetime", property="updatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    User selectByPrimaryKey(Integer id);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
        "update user",
        "set username = #{username,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "role = #{role,jdbcType=INTEGER},",
          "updatetime = now()",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);

    @Select({
            "select",
            "id, username, name, password, email, role, createtime, updatetime",
            "from user",
            "where username = #{username,jdbcType=VARCHAR}",
            "and password = #{password,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="role", property="role", jdbcType=JdbcType.INTEGER),
            @Result(column="createtime", property="createtime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updatetime", property="updatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    User login(@Param("username") String username, @Param("password") String password);

    @Select({
            "select",
            "id, username, name, password, email, role, createtime, updatetime",
            "from user",
            "order by updatetime desc"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="role", property="role", jdbcType=JdbcType.INTEGER),
            @Result(column="createtime", property="createtime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updatetime", property="updatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<User> getAllUser();

    @Select({
            "select",
            "id, username, name, password, email, role, createtime, updatetime",
            "from user",
            "where username = #{username,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="role", property="role", jdbcType=JdbcType.INTEGER),
            @Result(column="createtime", property="createtime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updatetime", property="updatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    User getUserByUserName(@Param("username") String username);

    @Select({
            "select",
            "id, username, name, password, email, role, createtime, updatetime",
            "from user",
            "where name like concat(concat('%',#{name}),'%')",
            "order by updatetime desc"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="role", property="role", jdbcType=JdbcType.INTEGER),
            @Result(column="createtime", property="createtime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updatetime", property="updatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<User> getUserListByName(String name);

    @Select({
            "select",
            "id, username, name, password, email, role, createtime, updatetime",
            "from user",
            "where role =#{role,jdbcType=INTEGER}",
            "and name like concat(concat('%',#{name}),'%')",
            "order by updatetime desc"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="role", property="role", jdbcType=JdbcType.INTEGER),
            @Result(column="createtime", property="createtime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updatetime", property="updatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<User> getStudentListByName(Integer role,String name);

    @Select({
            "select",
            "id, username, name, password, email, role, createtime, updatetime",
            "from user",
            "where username like concat(concat('%',#{username}),'%')",
            "order by updatetime desc"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="role", property="role", jdbcType=JdbcType.INTEGER),
            @Result(column="createtime", property="createtime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updatetime", property="updatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<User> getUserListByUserName(String username);

    @Select({
            "select",
            "id, username, name, password, email, role, createtime, updatetime",
            "from user",
            "where role =#{role,jdbcType=INTEGER}",
            "and username like concat(concat('%',#{username}),'%')",
            "order by updatetime desc"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="role", property="role", jdbcType=JdbcType.INTEGER),
            @Result(column="createtime", property="createtime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updatetime", property="updatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<User> getStudentListByUserName(Integer role,String username);

    @Select({
            "select",
            "id, username, name, password, email, role, createtime, updatetime",
            "from user",
            "where email like concat(concat('%',#{email}),'%')",
            "order by updatetime desc"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="role", property="role", jdbcType=JdbcType.INTEGER),
            @Result(column="createtime", property="createtime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updatetime", property="updatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<User> getUserListByEmail(String email);

    @Select({
            "select",
            "id, username, name, password, email, role, createtime, updatetime",
            "from user",
            "where role =#{role,jdbcType=INTEGER}",
            "and email like concat(concat('%',#{email}),'%')",
            "order by updatetime desc"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="role", property="role", jdbcType=JdbcType.INTEGER),
            @Result(column="createtime", property="createtime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updatetime", property="updatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<User> getStudentListByEmail(Integer role,String email);

    @Select({
            "select",
            "id, username, name, password, email, role, createtime, updatetime",
            "from user",
            "where role =#{role,jdbcType=INTEGER}",
            "order by updatetime desc"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="role", property="role", jdbcType=JdbcType.INTEGER),
            @Result(column="createtime", property="createtime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updatetime", property="updatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<User> getUserListByRole(Integer role);

    @Select({
            "select",
            "id, username, name, password, email, role, createtime, updatetime",
            "from user",
            "where email = #{email,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="role", property="role", jdbcType=JdbcType.INTEGER),
            @Result(column="createtime", property="createtime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updatetime", property="updatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    User getUserByEmail(@Param("email") String email);
}