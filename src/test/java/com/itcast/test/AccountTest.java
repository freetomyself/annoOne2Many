package com.itcast.test;

import com.itcast.dao.IAccountDao;
import com.itcast.dao.IUserDao;
import com.itcast.domain.Account;
import com.itcast.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

/**
 * @program: day04_eesy_03annotation_mybatis--com.itcast.test
 * @author: WaHotDog 2019-08-23 11:38
 **/


public class AccountTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IAccountDao accountDao;

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
        accountDao = sqlSession.getMapper(IAccountDao.class);
    }

    @After
    public void clean() throws Exception {
        //释放资源
        sqlSession.close();
        in.close();
    }

    @Test
    public void findAllTest() {
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println("---------这是一个账户----------");
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }


}
