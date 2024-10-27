package com.roam.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.roam.sys.entity.UserProgressRequest;
import com.roam.sys.entity.UserStats;
import com.roam.sys.entity.UserStatsRequest;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GJJ
 * @since 2024-10-09
 */
public interface IUserStatsService extends IService<UserStats> {

    Map<String, Object> updateOrInsertUserStats(UserStatsRequest userStatsReq);

    Map<String, Object> getUserProgress(UserProgressRequest userProgressReq);

    Map<String, Object> getUserHeatMapData(String token);



}
