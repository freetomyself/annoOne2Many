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

import java.io.InputStream;

/**
 * @program: day04_eesy_annoOne2Many--com.itcast.test
 * @author: WaHotDog 2019-08-23 18:40
 **/


public class SecondLevelCatchTest {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init() throws Exception {
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
        in.close();
    }

    @Test
    public void findByIdTest() {
        User user = userDao.findById(70);
        System.out.println(user);
        sqlSession.close();//释放一级缓存

        sqlSession = factory.openSession(true);
        userDao = sqlSession.getMapper(IUserDao.class);
        User user1 = userDao.findById(70);
        System.out.println(user1);
        System.out.println(user==user1);
    }
}
