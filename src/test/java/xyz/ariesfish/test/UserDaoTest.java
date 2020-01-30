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
import java.util.List;

public class UserDaoTest {

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
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println("------ User Info ------");
            System.out.println(user);
            System.out.println("------ Lazy Load Account Infos ------");
            System.out.println(user.getAccounts());
        }
    }

    @Test
    public void testFindOne() {
        User user = userDao.findById(4);
        System.out.println(user);
    }
}
