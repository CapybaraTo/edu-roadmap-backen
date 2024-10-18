package com.roam.sys.service;

import com.roam.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GJJ
 * @since 2024-10-09
 */
public interface IUserService extends IService<User> {
//service 方法注册
    Map<String, Object> login(User user);

    void signup(User user);

    Map<String, Object> getUserStats(String token);

    void logout(String token);

}
