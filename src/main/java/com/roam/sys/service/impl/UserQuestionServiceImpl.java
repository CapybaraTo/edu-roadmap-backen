package com.roam.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.roam.common.utils.JwtUtil;
import com.roam.common.vo.ComFoundException;
import com.roam.sys.entity.UserActivity;
import com.roam.sys.entity.UserQuestion;

import com.roam.sys.entity.UserQuestionRequest;
import com.roam.sys.entity.UserStats;
import com.roam.sys.mapper.UserQuestionMapper;
import com.roam.sys.service.IUserQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GJJ
 * @since 2024-10-09
 */
@Service
public class UserQuestionServiceImpl extends ServiceImpl<UserQuestionMapper, UserQuestion> implements IUserQuestionService {
    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    private UserQuestionMapper userQuestionMapper;

    //  注册
    @Override
    public Map<String ,Object> getUserQuestionProgress(UserQuestionRequest userQuestionRequest){
//        参数user是前端新接收的User对象
        Map<String, Object> data = new HashMap<>();
        data.put("know",userQuestionMapper.getUserKnowQuestionList(userQuestionRequest));
        data.put("dontKnow",userQuestionMapper.getUserDontKnowQuestionList(userQuestionRequest));
        data.put("skip",userQuestionMapper.getUserSkipQuestionList(userQuestionRequest));
        if(data == null){
            Map<String, Object> error = new HashMap<>();
            error.put("error","未获取到用户答题情况");
            return error;
        }
        return data;
    }

//    --stats
    @Override
    @Transactional
    public Map<String, Object> updateUserQuestionStatus(UserQuestion userQuestion){
//        1.找到已有记录   字段对应：userId, groupId, questionId
        QueryWrapper<UserQuestion> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userQuestion.getUserId())
                .eq("group_id",userQuestion.getGroupId())
                .eq("question_id",userQuestion.getQuestionId());
//        根据唯一标识 获取stats中已有的这条数据 及一个userQuestion对象
        UserQuestion existUserQuestion = userQuestionMapper.selectOne(queryWrapper);
//        1.2 修改已有记录
        if(existUserQuestion!=null) {
            //        原数据删除
            userQuestionMapper.delete(queryWrapper);
            //        修改新数据对象
            existUserQuestion.setStatus(userQuestion.getStatus());
            existUserQuestion.setUpdateAt(new Date());
            //        插入新数据对象
            userQuestionMapper.insert(existUserQuestion);
        }else{
//            2.原来没有 直接新增
            UserQuestion newUserQuestion = new UserQuestion();
            newUserQuestion.setUserId(userQuestion.getUserId());
            newUserQuestion.setGroupId(userQuestion.getGroupId());
            newUserQuestion.setQuestionId(userQuestion.getQuestionId());
            newUserQuestion.setStatus(userQuestion.getStatus());
            newUserQuestion.setUpdateAt(new Date());
            userQuestionMapper.insert(newUserQuestion);
        }
//        3.封装返回数据  更新完成后的用户答题状态3个string数组
        Map<String, Object> data = new HashMap<>();
        UserQuestionRequest userQuestionRequest = new UserQuestionRequest();
        userQuestionRequest.setUserId(userQuestion.getUserId());
        userQuestionRequest.setGroupId(userQuestion.getGroupId());
        data.put("know",userQuestionMapper.getUserKnowQuestionList(userQuestionRequest));
        data.put("dontKnow",userQuestionMapper.getUserDontKnowQuestionList(userQuestionRequest));
        data.put("skip",userQuestionMapper.getUserSkipQuestionList(userQuestionRequest));
        if(data == null){
            Map<String, Object> error = new HashMap<>();
            error.put("error","未获取到用户答题情况");
            return error;
        }
        return data;
    }

//   重置用户答题状态
//    设置用户所有题目状态为reset  表示做过了但重置了
    @Override
    public Map<String, Object> resetUserQuestionProgress(UserQuestionRequest userQuestionRequest){

        userQuestionMapper.resetUserQuestionProgress(userQuestionRequest.getUserId(),userQuestionRequest.getGroupId());
        Map<String, Object> data = new HashMap<>();
        data.put("know",userQuestionMapper.getUserKnowQuestionList(userQuestionRequest));
        data.put("dontKnow",userQuestionMapper.getUserDontKnowQuestionList(userQuestionRequest));
        data.put("skip",userQuestionMapper.getUserSkipQuestionList(userQuestionRequest));
        return data;
    }


}
