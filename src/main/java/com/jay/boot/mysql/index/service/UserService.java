package com.jay.boot.mysql.index.service;

import com.jay.boot.common.enums.ResultEnum;
import com.jay.boot.common.exception.CustException;
import com.jay.boot.mysql.index.entity.User;
import com.jay.boot.mysql.index.mapper.UserMapper;
import com.jay.boot.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * ClassName UserService
 *
 * @author shao
 * @since 1.0
 * Date 2019/7/31 21:16
 */
@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    public String login(Map<String, String> paramsMap) {
        String username = paramsMap.get("username");
        String password = paramsMap.get("password");
        User user = userMapper.findUserByName(username);
        if (user == null || !StringUtils.equals(user.getPassword(), password)) {
            throw new CustException(ResultEnum.FAIL);
        }
        String id = user.getId();
        String token = TokenUtil.getToken(id, password);
        return token;
    }
    
    public User getUserById(Map<String, String> paramsMap) {
        String id = paramsMap.get("id");
        User user = userMapper.findUserById(id);
        return user;
    }
    
    public User getUserByName(Map<String, String> paramsMap) {
        String username = paramsMap.get("username");
        User user = userMapper.findUserByName(username);
        return user;
    }
}
