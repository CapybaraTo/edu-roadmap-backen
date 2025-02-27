package com.roam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan({"com.roam.*.mapper"})
@ComponentScan({"com.roam", "com.video"})
public class RoadmapBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoadmapBackendApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
