package com.roam.sys.controller;

import com.roam.sys.entity.User;
import com.roam.sys.entity.UserProgressRequest;
import com.roam.sys.entity.UserStatsRequest;
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
@RequestMapping("/resource")
public class UserStatsController {
    @Autowired
    private IUserStatsService userStatsService;

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

    @PostMapping("/update-user-progress")
    public Map<String, Object> updateOrInsertUserStats(@RequestBody UserStatsRequest request){
//        传一个UserStatsRequest 类型数据 包括userid username resourcetype resourcetitle topicid topictitle status
//        它与user_stats的区别在于status的值以及没有updateat这个值
        Map<String, Object> data = userStatsService.updateOrInsertUserStats(request);
        return data;
    }

    @PostMapping("/get-user-progress")
    public Map<String, Object> getUserProgress(
            @RequestBody UserProgressRequest userProgressReq){
//            @RequestBody HttpServletRequest request,
////            @RequestParam("token") String token,
//            @RequestParam("resourceType") String resourceType,
//            @RequestParam("resourceId") String resourceId){
//        String token = getCookieValue(request,"__roadmapsh_jt__");
//        User loginUser = null;
//        try {
//            loginUser = jwtUtil.parseToken(token, User.class);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        if(loginUser != null) {
//            UserProgressRequest userProgressReq = new UserProgressRequest();
//            userProgressReq.setUserId(loginUser.getId());
//            userProgressReq.setResourceType(resourceType);
//            userProgressReq.setResourceId(resourceId);
            Map<String, Object> data = userStatsService.getUserProgress(userProgressReq);
            return data;
//        }
//        else{
//            Map<String, Object> error2 = new HashMap<>();
//            error2.put("error","登录信息无效");
//            return error2;
//        }
    }



}

