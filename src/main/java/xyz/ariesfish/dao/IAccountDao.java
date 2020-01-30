package xyz.ariesfish.dao;

import xyz.ariesfish.domain.Account;

import java.util.List;

public interface IAccountDao {

    /**
     * 查询所有帐户，同时获取当前账户的所属用户信息
     * @return
     */
    List<Account> findAll();
}
