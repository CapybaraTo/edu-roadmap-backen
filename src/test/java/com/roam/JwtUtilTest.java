package com.roam;

import com.roam.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import com.roam.sys.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

//    生成jwt token
    @Test
    public void testCreateJwt(){
        User user = new User();
        user.setUsername("zhangyi");
        user.setPhone("123456789");
        String token = jwtUtil.createToken(user);
        System.out.println((token));
    }

//    测试解析jwt token
    @Test
    public void testParseJwt(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJkY2M5NGQ5NC05YTJhLTRhMWYtYWY5ZC0wNWNlMWRiYTg0NTkiLCJzdWIiOiJ7XCJwaG9uZVwiOlwiMTIzNDU2Nzg5XCIsXCJ1c2VybmFtZVwiOlwiemhhbmd5aVwifSIsImlzcyI6InN5c3RlbSIsImlhdCI6MTcyODU0ODYzOSwiZXhwIjoxNzI4NTUwNDM5fQ.sppI0bXRm64hJF71se3hdW4dAJxGo28n5F-pTiG5SkU";
//        Claims claims = jwtUtil.parseToken(token);
        User user = jwtUtil.parseToken(token, User.class);
        System.out.println(user);
    }
}
