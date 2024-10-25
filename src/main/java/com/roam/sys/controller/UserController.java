package com.roam.sys.controller;

import com.roam.common.vo.Result;
import com.roam.sys.entity.User;
import com.roam.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//@CrossOrigin   //解决前后端对接跨域的问题
public class UserController {
    @Autowired
    private IUserService userService;

//   请求查询所有数据
    @GetMapping("/all")
//    方法-返回所有数据
    public Result<List<User>> getAllUser(){
        List<User> list = userService.list();
        return Result.success(list,"查询成功");
    }

//    获取用户状态
private String getCookieValue(HttpServletRequest request, String cookieName) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookieName.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
    }
    return null;
}
    @GetMapping("/stats")
    public Map<String, Object> getUserStats(HttpServletRequest request){
        String token = getCookieValue(request,"__roadmapsh_jt__");
//检查是否找到了token
        if (token == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "token=null 登录信息无效，请重新登录");
            return error;
        }
        // 根据token获取用户信息
        Map<String,Object> data = userService.getUserStats(token);
        Map<String, Object> error = new HashMap<>();
        error.put("error","error2 登录信息无效，请重新登陆");
        if(data != null){
            return data;
        }
        return error;
    }

//    登录控制器
    @PostMapping("/login")
//    data中的数据是map键值对
//    如果参数只写成login(User user)参数是以json字符串的形式，需要加注解，转换成对象
//    public Result<Map<String, Object>> login(@RequestBody User user){
////        检查数据库里有没有该用户--放到service里面做，直接调用service 的方法。alt + enter
//        Map<String, Object> data = userService.login(user);
//        if(data != null){
////            查询成功
//            return Result.success(data);
//        }
//        return Result.fail(202,"用户名或密码错误");
//    }
    public Map<String, Object> login(@RequestBody User user){
//        检查数据库里有没有该用户--放到service里面做，直接调用service 的方法。alt + enter
//        用户名唯一
        Map<String, Object> data = userService.login(user);
        Map<String, Object> error = new HashMap<>();
        error.put("error","invalid credential");
        if(data != null){
//            查询成功
            return data;
        }
        return error;
    }

//注册 @requestbody把json数据转换为user对象
    @PostMapping("/signup")
    public Result<?> signup(@RequestBody User user){
        userService.signup(user);
        return Result.success(200,"status ok");
    }

//    注销
    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("X-Token") String token){
        userService.logout(token);
        return Result.success("退出登录成功");
    }
}
