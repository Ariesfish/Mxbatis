package xyz.ariesfish.dao;

import org.apache.ibatis.annotations.Select;
import xyz.ariesfish.domain.User;

import java.util.List;

public interface IUserDao {

    @Select("select * from user")
    List<User> findAll();
}
