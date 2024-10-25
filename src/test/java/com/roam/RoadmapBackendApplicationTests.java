package com.roam;

import com.roam.sys.entity.User;
import com.roam.sys.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class RoadmapBackendApplicationTests {

    @Resource   //注入依赖
    private UserMapper userMapper;
    @Test
//   数据表的查询 作为测试
    void testMapper() {
        List<User> users = userMapper.selectList(null);   // userMapper.selectList(null);  按下alt+enter快捷键生成变量
        users.forEach(System.out::println);   //打印出来遍历
    }

}
