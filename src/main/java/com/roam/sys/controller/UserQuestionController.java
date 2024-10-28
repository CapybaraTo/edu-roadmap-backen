package com.roam.sys.controller;

import com.roam.sys.entity.*;
import com.roam.sys.service.IUserQuestionService;
import com.roam.sys.service.IUserStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.roam.common.utils.JwtUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author GJJ
 * @since 2024-10-26
 */
@RestController
@RequestMapping("/question")
public class UserQuestionController {
    @Autowired
    private IUserQuestionService userQuestionService;

    @Autowired
    private JwtUtil jwtUtil;

    private String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    @PostMapping("/get-question-progress")
    public Map<String ,Object> getUserQuestionProgress(@RequestBody UserQuestionRequest userQuestionRequest){
//        post请求可以用requestbody get不行
        Map<String, Object> data = userQuestionService.getUserQuestionProgress(userQuestionRequest);
        return data;
    }

    @PutMapping("/update-question-status")
    public Map<String,Object> updateUserQuestionStatus(@RequestBody UserQuestion userQuestion){
        Map<String, Object> data = userQuestionService.updateUserQuestionStatus(userQuestion);
        return data;
    }

    @PutMapping("/reset-question-progress")
    public Map<String, Object> restUserQuestionProgress(@RequestBody UserQuestionRequest userQuestionRequest){
//        把所有已记录过的题目状态设置为reset
//        也需要返回know dontknow skip三个string数组  --或许可以知道哪些是做过然后重置的？
        Map<String, Object> data = userQuestionService.resetUserQuestionProgress(userQuestionRequest);
        return data;
    }

}

