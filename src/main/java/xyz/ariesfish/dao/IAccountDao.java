package xyz.ariesfish.dao;

import xyz.ariesfish.domain.Account;
import xyz.ariesfish.domain.AccountUser;

import java.util.List;

public interface IAccountDao {

    /**
     * 查询所有帐户，同时获取当前账户的所属用户信息
     * @return
     */
    List<Account> findAll();

    /**
     * 查询所有账户，同时包含用户名和地址信息
     * @return
     */
    List<AccountUser> findAllAccount();
}
