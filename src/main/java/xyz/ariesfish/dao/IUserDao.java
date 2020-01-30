package xyz.ariesfish.dao;

import org.apache.ibatis.annotations.*;
import xyz.ariesfish.domain.QueryVo;
import xyz.ariesfish.domain.User;

import java.util.List;

public interface IUserDao {

    List<User> findAll();

    User findById(Integer id);

    List<User> findByName(String username);

    /**
     * 根据QueryVo中的条件查询用户
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);

    /**
     * 根据传入参数条件查询
     * @param user
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 根据QueryVo中的ID集合查询
     * @param vo
     * @return
     */
    List<User> findUserInIds(QueryVo vo);
}
