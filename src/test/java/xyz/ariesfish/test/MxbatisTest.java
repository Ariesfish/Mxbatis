package xyz.ariesfish.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import xyz.ariesfish.dao.IUserDao;
import xyz.ariesfish.domain.QueryVo;
import xyz.ariesfish.domain.User;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MxbatisTest {

    private InputStream in;
    private SqlSession session;
    private IUserDao userDao;

    /**
     * 1. read configuration
     * 2. create SqlSessionFactory
     * 3. use factory generate SqlSession
     * 4. use SqlSession create proxy of DAO
     * 5. execute proxy
     * 6. release resource
     */

    @Before
    public void init() throws Exception {
        // 1. read configuration
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2. create SqlSessionFactory
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        // 3. use factory generate SqlSession
        session = factory.openSession();
        // 4. use SqlSession create proxy of DAO 创建DAO接口的代理对象
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws Exception {
        // 需要提交事务
        session.commit();

        // 6. release resource
        session.close();
        in.close();
    }

    @Test
    public void testFindAll() {
        // 5. execute proxy 执行DAO中的方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("Gu Ping");
        user.setAddress("Suzhou");
        user.setSex("F");
        user.setBirthday(new Date());

        System.out.println(user);
        userDao.saveUser(user);
        System.out.println(user);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(5);
        user.setUsername("Wang Qiang");
        user.setAddress("Hongkong");
        user.setSex("M");
        user.setBirthday(new Date());

        userDao.updateUser(user);
    }

    @Test
    public void testDelete() {
        userDao.deleteUser(5);
    }

    @Test
    public void testFindOne() {
        User user = userDao.findById(4);
        System.out.println(user);
    }

    /**
     * 模糊查询
     */
    @Test
    public void testFindByName() {
        List<User> users = userDao.findByName("%林%");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindTotal() {
        int count = userDao.findTotal();
        System.out.println(count);
    }

    @Test
    public void testFindByVo() {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%林%");
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }
}
