package com.roam.sys.mapper;

import com.roam.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roam.sys.model.UserActivityStream;
import com.roam.sys.model.UserDashBoard;
import com.roam.sys.model.UserRoadmapStats;
import com.roam.sys.model.UserStreak;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GJJ
 * @since 2024-10-09
 */
public interface UserMapper extends BaseMapper<User> {

//    public List<String> getUserStreakByUserId(Integer userId);

    public UserRoadmapStats getUserRoadmapStatsByUserId(Integer userId);

    public UserStreak getUserStreakByUserId(Integer userId);

    public UserDashBoard getUserDashBoardInfoByUserId(Integer userId);

    public UserActivityStream getUserActivityByUserId(Integer userId);

}