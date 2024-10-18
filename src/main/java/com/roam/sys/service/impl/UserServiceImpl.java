package com.roam.sys.service.impl;

import com.roam.sys.entity.User;
import com.roam.sys.mapper.UserMapper;
import com.roam.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GJJ
 * @since 2024-10-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
