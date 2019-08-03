package com.jay.boot.mysql.index.mapper;

import com.jay.boot.mysql.index.entity.User;

/**
 * ClassName UserMapper
 *
 * @author shao.meng
 * @since 1.0
 * Date 2019/7/31 20:55
 */
public interface UserMapper {
    
    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    User findUserById(String id);
    
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findUserByName(String username);
}
