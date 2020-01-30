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
import java.util.ArrayList;
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
    public void testFindOne() {
        User user = userDao.findById(4);
        System.out.println(user);
    }

    /**
     * 模糊查询
     */
    @Test
    public void testFindByName() {
        List<User> users = userDao.findByName("%Lin%");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindByVo() {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%Lin%");
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void testFindByCondition() {
        User u = new User();
        //u.setUsername("Lin Feng");
        u.setSex("M");
        List<User> users = userDao.findUserByCondition(u);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindUserInIds() {
        QueryVo vo = new QueryVo();
        List<Integer> idList = new ArrayList<Integer>();
        idList.add(2);
        idList.add(4);
        idList.add(8);
        vo.setIds(idList);

        List<User> users = userDao.findUserInIds(vo);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
