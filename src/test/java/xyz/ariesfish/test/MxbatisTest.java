package xyz.ariesfish.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import xyz.ariesfish.dao.IUserDao;
import xyz.ariesfish.domain.User;

import java.io.InputStream;
import java.util.List;

public class MxbatisTest {

    public static void main(String[] args) {
        /**
         * 1. read configuration
         * 2. create SqlSessionFactory
         * 3. use factory generate SqlSession
         * 4. use SqlSession create proxy of DAO
         * 5. execute proxy
         * 6. release resource
         */
        try {
            // 1. read configuration
            InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
            // 2. create SqlSessionFactory
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            // 3. use factory generate SqlSession
            SqlSession session = factory.openSession();
            // 4. use SqlSession create proxy of DAO 创建DAO接口的代理对象
            IUserDao userDao = session.getMapper(IUserDao.class);
            // 5. execute proxy 执行DAO中的方法
            List<User> users = userDao.findAll();
            for (User user : users) {
                System.out.println(user);
            }
            // 6. release resource
            session.close();
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

        }
    }
}