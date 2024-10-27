package com.roam.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roam.common.utils.JwtUtil;
import com.roam.sys.entity.*;
import com.roam.sys.mapper.UserActivityMapper;
import com.roam.sys.mapper.UserStatsMapper;
import com.roam.sys.entity.UserStatsRequest;
import com.roam.sys.entity.UserProgressRequest;
import com.roam.sys.service.IUserStatsService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserStatsServiceImpl extends ServiceImpl<UserStatsMapper, UserStats> implements IUserStatsService {
    @Autowired
    private UserStatsMapper userStatsMapper;

    @Autowired
    private UserActivityMapper userActivityMapper;

    @Autowired
    private JwtUtil jwtUtil;

//    更新用户路线图状态
    @Override
    @Transactional
    public Map<String, Object> updateOrInsertUserStats(UserStatsRequest userStatsReq){
//        String userId, String username, String resourceType, String resourceId, String resourceTitle, String topicId, String topicTitle, String status

//        userStatsmapper是数据库最基础的直接映射式的增删改查，.xml里的是封装的一些复杂查询，需要在上面的mapper中注册
//        userStatsService写具体服务内容，需要在IUserStatsService中注册以及接口暴露出去

//        前端传递的数据包括：userId、resourceType、resourceId、topicId、status，

//        先查询是否已经有记录
//        String userId = userStats.getUserId();
//        String resourceType = userStats.getResourceType();
//        String resourceId = userStats.getResourceId();
//        String topicId = userStats.getTopicId();
        QueryWrapper<UserStats> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userStatsReq.getUserId())
                    .eq("resource_type",userStatsReq.getResourceType())
                    .eq("resource_id",userStatsReq.getResourceId())
                    .eq("topic_id",userStatsReq.getTopicId());
//        根据唯一标识 获取stats中已有的这条数据 及一个userstats对象
        UserStats existUserStats = userStatsMapper.selectOne(queryWrapper);
//        用户更新stats表 把原数据删除
        userStatsMapper.delete(queryWrapper);
//        userId、resourceType、resourceId、topicId确定唯一一条记录
//        UserStats existUserStats = userStatsMapper.findUserStats(userId, resourceType, resourceId, topicId, status, LocalDateTime.now());
        UserActivity newUserActivity = new UserActivity();   // 创建活动实体，更新完stats后添加活动信息
        if(existUserStats!=null){
            existUserStats.setTopicStats(userStatsReq.getProgress());
            existUserStats.setUpdatedAt(new Date());
            userStatsMapper.insert(existUserStats);

//            插入activity
            newUserActivity.setUserId(existUserStats.getUserId());
            newUserActivity.setUsername(existUserStats.getUsername());
            newUserActivity.setResourceType(existUserStats.getResourceType());
            newUserActivity.setResourceId(existUserStats.getResourceId());
            newUserActivity.setResourceTitle(existUserStats.getResourceId());
            newUserActivity.setTopicId(existUserStats.getTopicId());
            newUserActivity.setTopicTitle(existUserStats.getTopicId());
            newUserActivity.setStatus(existUserStats.getTopicStats());
            newUserActivity.setCreatedAt(existUserStats.getUpdatedAt());
            userActivityMapper.insert(newUserActivity);
//            更新stats
//            LambdaQueryWrapper<UserStats> updateWrapper = new LambdaQueryWrapper<UserStats>();
//            updateWrapper.eq(UserStats::getUserId,userStatsReq.getUserId())
//                         .eq(UserStats::getResourceType,userStatsReq.getResourceType())
//                         .eq(UserStats::getResourceId,userStatsReq.getResourceId())
//                         .eq(UserStats::getTopicId,userStatsReq.getTopicId());
        }else{
//            如果没有之前的记录，新学的知识点，创建赋值并插入新的stats信息
            UserStats newUserStats = new UserStats();
            newUserStats.setUserId(userStatsReq.getUserId());
            newUserStats.setUsername(userStatsReq.getUsername());
            newUserStats.setResourceType(userStatsReq.getResourceType());
            newUserStats.setResourceId(userStatsReq.getResourceId());
            newUserStats.setResourceTitle(userStatsReq.getResourceId());
            newUserStats.setTopicId(userStatsReq.getTopicId());
            newUserStats.setTopicTitle(userStatsReq.getTopicId());
            newUserStats.setTopicStats(userStatsReq.getProgress());
            newUserStats.setUpdatedAt(new Date());
            userStatsMapper.insert(newUserStats);
//             同步赋值插入一条activity数据
            newUserActivity.setUserId(newUserStats.getUserId());
            newUserActivity.setUsername(newUserStats.getUsername());
            newUserActivity.setResourceType(newUserStats.getResourceType());
            newUserActivity.setResourceId(newUserStats.getResourceId());
            newUserActivity.setResourceTitle(newUserStats.getResourceTitle());
            newUserActivity.setTopicId(newUserStats.getTopicId());
            newUserActivity.setTopicTitle(newUserStats.getTopicTitle());
            newUserActivity.setStatus(newUserStats.getTopicStats());
            newUserActivity.setCreatedAt(newUserStats.getUpdatedAt());
            userActivityMapper.insert(newUserActivity);
        }
//        stats更新/插入完成后  同步更新activity
        UserProgressRequest userProgressReq = new UserProgressRequest();
        userProgressReq.setUserId(userStatsReq.getUserId());
        userProgressReq.setResourceType(userStatsReq.getResourceType());
        userProgressReq.setResourceId(userStatsReq.getResourceId());
        List<String> userDoneResourceList = this.baseMapper.getUserDoneResourceList(userProgressReq);
        List<String> userLearningResourceList = this.baseMapper.getUserLearningResourceList(userProgressReq);
        List<String> userSkippedResourceList = this.baseMapper.getUserSkippedResourceList(userProgressReq);
//        Boolean userIsFavorite = this.baseMapper.getUserIsFavorite

        Map<String, Object> data = new HashMap<>();
        data.put("done",userDoneResourceList);
        data.put("learning",userLearningResourceList);
        data.put("skipped",userSkippedResourceList);
        return data;

    }
//     获取当前用户的progress
    @Override
    public Map<String, Object> getUserProgress(UserProgressRequest userProgressReq){
//        UserProgressRequest  包括userId或者是token和当前的resourceType和当前的resourceId
//    前端只用发resourceType和resourceId   后端再自动从cookie中获取并解析token得到userId
//        注意controller部分获取前端cookie参考stats
        List<String> userDoneResourceList = this.baseMapper.getUserDoneResourceList(userProgressReq);
        List<String> userLearningResourceList = this.baseMapper.getUserLearningResourceList(userProgressReq);
        List<String> userSkippedResourceList = this.baseMapper.getUserSkippedResourceList(userProgressReq);
//        Boolean userIsFavorite = this.baseMapper.getUserIsFavorite
//        QueryWrapper<UserFavorite> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("resourceId",)
        Map<String, Object> data = new HashMap<>();
        data.put("done",userDoneResourceList);
        data.put("learning",userLearningResourceList);
        data.put("skipped",userSkippedResourceList);
//        data.put("isFavorite",)
        return data;
    }





}
