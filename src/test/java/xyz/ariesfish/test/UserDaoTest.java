package xyz.ariesfish.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import xyz.ariesfish.dao.IUserDao;
import xyz.ariesfish.domain.User;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserDaoTest {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;


    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws Exception {
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testFindAll() {
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("Hu Ping");
        user.setAddress("Hangzhou");
        userDao.saveUser(user);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(9);
        user.setUsername("Hu Ping");
        user.setAddress("Hangzhou");
        user.setSex("M");
        user.setBirthday(new Date());
        userDao.updateUser(user);
    }

    @Test
    public void testDeleteUser() {
        userDao.deleteUser(9);
    }

    @Test
    public void testFindById() {
        User user = userDao.findById(4);
        System.out.println(user);
    }

    @Test
    public void testFindUserByName() {
        List<User> users = userDao.findUserByName("%ng%");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindTotalUser() {
        int count = userDao.findTotalUser();
        System.out.println(count);
    }
}
