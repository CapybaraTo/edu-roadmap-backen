package com.roam.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.roam.sys.entity.*;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GJJ
 * @since 2024-10-09
 */
public interface IUserQuestionService extends IService<UserQuestion> {

    Map<String, Object> updateUserQuestionStatus(UserQuestion userQuestion);

    Map<String, Object> getUserQuestionProgress(UserQuestionRequest userQuestionRequest);

    Map<String, Object> resetUserQuestionProgress(UserQuestionRequest userQuestionRequest);






}
