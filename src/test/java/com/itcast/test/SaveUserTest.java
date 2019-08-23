package com.itcast.test;

import com.itcast.dao.IUserDao;
import com.itcast.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: day04_eesy_03annotation_mybatis--com.itcast.test
 * @author: WaHotDog 2019-08-23 11:38
 **/


public class SaveUserTest{
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IUserDao userDao;
    @Before
    public void init()  throws  Exception{
        //1添加配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2使用SqlSessionFactoryBuilder创建工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        //创建sqlSession工厂
        sqlSession = factory.openSession(true);
        //使用sqlSession创建dao代理工厂
        userDao = sqlSession.getMapper(IUserDao.class);
    }
    @After
    public void clean() throws Exception {
        //释放资源
        sqlSession.close();
        in.close();
    }


    @Test
    public void findAllTest() throws ParseException {
        List<User> users = userDao.finAll();
        for (User user : users) {
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }

    @Test
    public void findByIdTest(){
        User user = userDao.findById(70);
        System.out.println(user);
    }

    @Test
    public void findUserByName(){
        //字符串拼接
        List<User> users = userDao.findUserByName( "%三%" );
        //占位符
//        List<User> users = userDao.findUserByName("张");
        for (User user : users) {
            System.out.println(user);
        }
    }


}
