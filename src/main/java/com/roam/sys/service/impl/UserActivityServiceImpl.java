package com.roam.sys.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.roam.common.utils.JwtUtil;
import com.roam.common.vo.ComFoundException;
import com.roam.sys.entity.User;
import com.roam.sys.mapper.UserMapper;
import com.roam.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

//  注册
    @Override
    public void signup(User user){
        String username = user.getUsername();
        String password = user.getPassword();
//        查询是否存在已有用户名
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User tu =  this.baseMapper.selectOne(queryWrapper);
        if(tu!=null){
            throw new ComFoundException("用户名已经被注册");
        }
//        密码md5加密
        user.setPassword(passwordEncoder.encode(password));
//        创建账号的时间
        user.setCreatedat(new Date());
//        TODO用户状态
        user.setStatus(1);
        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new ComFoundException("添加数据失败");
        }

    }

//    登录
    @Override
    public Map<String, Object> login(User user){
//        根据电话和密码查询
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, user.getUsername());  // 根据用户名查询  用户名唯一
        User loginUser = this.baseMapper.selectOne(wrapper);  //得到登录的用户信息
//        查询结果不为空，且密码匹配
        if(loginUser != null && passwordEncoder.matches(user.getPassword(), loginUser.getPassword())){
//            jwt
            loginUser.setPassword(null);
            String jwt = jwtUtil.createToken(loginUser);
//            要生成token,暂时用UUID.应该用jwt
//            String key = "" + UUID.randomUUID();   // 拼接上用户
//            存入redis。需要启动redis
//            loginUser.setPassword(null);
//            redisTemplate.opsForValue().set(key, loginUser,60, TimeUnit.MINUTES);  // 超时60分钟退出

//            返回数据
            Map<String, Object> data = new HashMap<>();
            data.put("token", jwt);  // 键值对
            return data;
        }
        return null;
    }

//获取用户信息   --改为获取用户收藏&用户状态   --跟视频，学习联表查询

    @Override
    public Map<String, Object> getUserStats(String token) {
//      从redis中拿到用户登录信息 反序列化
//      Object obj = redisTemplate.opsForValue().get(token);
        User loginUser = null;
        try {
            loginUser = jwtUtil.parseToken(token, User.class);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(loginUser != null){
//            redis拿出 反序列化
//            User loginUser = JSON.parseObject(JSON.toJSONString(obj),User.class);
            Map<String, Object> data = new HashMap<>();
            data.put("userId",loginUser.getId());
            data.put("username",loginUser.getUsername());
            data.put("avatar", loginUser.getAvatar());

//            获取用户信息：学习天数、done数量、learning map数量
            List<String> userStreak = this.baseMapper.getUserStreakByUserId(loginUser.getId());
            data.put("streak",userStreak);
            return data;
        }
        return null;
    }

//    登出  --前端处理 删除token
    @Override
    public void logout(String token){
//        redisTemplate.delete(token);
    }



}
