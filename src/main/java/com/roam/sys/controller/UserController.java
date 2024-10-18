package com.roam.sys.controller;

import com.roam.sys.entity.User;
import com.roam.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author GJJ
 * @since 2024-10-09
 */
//@Controller  前后端交互是json数据，controller注解是返回视图
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

//   请求查询所有数据
    @GetMapping("/all")
//    方法-返回所有数据
    public List<User> getAllUser(){
        List<User> list = userService.list();
        return list;
    }
}
