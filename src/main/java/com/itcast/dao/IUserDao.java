package com.itcast.dao;

import com.itcast.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @program: day04_eesy_03annotation_mybatis--com.itcast.dao
 * @author: WaHotDog 2019-08-23 08:52
 **/

/**
 * 在mybatis中针对，CRUD一共有四个注解
 *
 * @Selsect @Insert @Update @Delete
 */
public interface IUserDao {

    /**
     * 查询所有用户
     *
     * @return
     */


    @Select("select * from user ")
  /* // 一对一
    @Results(id = "userMap",value = {
            @Result(id = true,column ="id",property = "userid"),
            @Result( column ="username",property = "userName"),
            @Result( column ="address",property = "useraddress"),
            @Result( column ="sex",property = "usersex"),
            @Result( column ="birthday",property = "userbirthday")
    })*/
  //一对多
    @Results(id = "userMap", value = {
            @Result(id = true, column = "id", property = "userid"),
            @Result(id = true, column = "username", property = "userName"),
            @Result(id = true, column = "address", property = "useraddress"),
            @Result(id = true, column = "sex", property = "usersex"),
            @Result(id = true, column = "birthday", property = "userbirthday"),
            /*
            * property 为主表中所创建的从表的 private List<Account> accounts
            * */
            @Result(property = "accounts", column = "id", many = @Many(select = "com.itcast.dao.IAccountDao.findAccountById", fetchType = FetchType.LAZY))

    })
    public List<User> finAll();

    /**
     * 通过id查用户
     *
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    @ResultMap(value = {"userMap"})
    public User findById(Integer id);

    /**
     * 通过名字查询用户
     *
     * @param name
     * @return
     */
    //字符串拼接占位符
    @Select("select * from user where username like #{username}")
    @ResultMap(value = {"userMap"})
    //字符串拼接
//    @Select("select * from user where username like '%${value}%' ")
    public List<User> findUserByName(String name);


}
