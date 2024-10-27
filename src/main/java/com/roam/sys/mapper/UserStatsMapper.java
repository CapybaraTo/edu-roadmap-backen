package com.roam.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roam.sys.entity.UserProgressRequest;
import com.roam.sys.entity.UserStats;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GJJ
 * @since 2024-10-09
 */
public interface UserStatsMapper extends BaseMapper<UserStats> {
//    public UserStreak getUserStreakByUserId(Integer userId);
    public List<String> getUserDoneResourceList(UserProgressRequest userProgressReq);

    public List<String> getUserLearningResourceList(UserProgressRequest userProgressReq);

    public List<String> getUserSkippedResourceList(UserProgressRequest userProgressReq);



}
