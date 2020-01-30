package xyz.ariesfish.dao;

import xyz.ariesfish.domain.Role;

import java.util.List;

public interface IRoleDao {

    List<Role> findAll();
}
