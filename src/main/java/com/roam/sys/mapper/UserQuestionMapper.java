package com.roam.sys.mapper;

import com.roam.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roam.sys.entity.UserProgressRequest;
import com.roam.sys.entity.UserQuestion;
import com.roam.sys.entity.UserQuestionRequest;
import com.roam.sys.model.UserActivityStream;
import com.roam.sys.model.UserDashBoard;
import com.roam.sys.model.UserRoadmapStats;
import com.roam.sys.model.UserStreak;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GJJ
 * @since 2024-10-09
 */
@Mapper
public interface UserQuestionMapper extends BaseMapper<UserQuestion> {

//    public List<String> getUserStreakByUserId(String userId);

    //    获取用户在某问题组的答题情况  三种状态：know dontkown skip
    public List<String> getUserKnowQuestionList(UserQuestionRequest userQuestionRequest);

    public List<String> getUserDontKnowQuestionList(UserQuestionRequest userQuestionRequest);

    public List<String> getUserSkipQuestionList(UserQuestionRequest userQuestionRequest);

    @Update("UPDATE user_question SET status = 'reset' WHERE user_id = #{userId} AND group_id = #{groupId}")
    void resetUserQuestionProgress(@Param("userId") String userId, @Param("groupId") String groupId);






}
