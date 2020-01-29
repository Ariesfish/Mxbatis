package xyz.ariesfish.dao;

import xyz.ariesfish.domain.User;
import xyz.ariesfish.mxbatis.annotations.Select;

import java.util.List;

public interface IUserDao {
    @Select("select * from user")
    List<User> findAll();
}
