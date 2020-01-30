package xyz.ariesfish.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import xyz.ariesfish.dao.IAccountDao;
import xyz.ariesfish.domain.Account;
import xyz.ariesfish.domain.AccountUser;

import java.io.InputStream;
import java.util.List;

public class AccountDaoTest {
    private InputStream in;
    private SqlSession session;
    private IAccountDao accountDao;

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
        accountDao = session.getMapper(IAccountDao.class);
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
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindAllAccount() {
        List<AccountUser> accountUsers = accountDao.findAllAccount();
        for (AccountUser au : accountUsers) {
            System.out.println(au);
        }
    }
}
