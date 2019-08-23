package com.itcast.dao;

import com.itcast.domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @program: day04_eesy_annoOne2Many--com.itcast.dao
 * @author: WaHotDog 2019-08-23 16:58
 **/


public interface IAccountDao {

    /**
     * 查询所有账户
     *
     * @return
     */
    @Select("select * from account")
    @Results(id = "accountMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "money", property = "money"),
            @Result(property = "user", column = "uid", one = @One(select = "com.itcast.dao.IUserDao.findById", fetchType = FetchType.EAGER))

    })
    public List<Account> findAll();

    @Select("select * from account where uid = #{uid}")
    public List<Account> findAccountById(Integer id);
}
