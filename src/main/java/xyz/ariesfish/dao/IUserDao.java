package xyz.ariesfish.dao;

import xyz.ariesfish.domain.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();
}
