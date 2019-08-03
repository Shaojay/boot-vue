package com.jay.boot.mysql.index.controller;

import com.jay.boot.common.config.annotation.VerifyToken;
import com.jay.boot.common.enums.ResultEnum;
import com.jay.boot.mysql.index.entity.User;
import com.jay.boot.mysql.index.service.UserService;
import com.jay.boot.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ClassName LoginController
 *
 * @author shao
 * @since 1.0
 * Date 2019/6/6 21:59
 */
@RestController
@RequestMapping("/user")
public class LoginController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public ResultUtil login(@RequestBody Map<String, String> paramsMap) {
        ResultUtil<String> result = new ResultUtil<>(ResultEnum.SUCCESS);
        result.setResult(userService.login(paramsMap));
        return result;
    }
    
    @PostMapping("/getUserByName")
    @VerifyToken
    public ResultUtil<User> getUserByName(@RequestBody Map<String, String> paramsMap) {
        ResultUtil<User> result = new ResultUtil<>(ResultEnum.SUCCESS);
        result.setResult(userService.getUserByName(paramsMap));
        return result;
    }
}
