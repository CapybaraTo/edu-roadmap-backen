package com.roam;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        String url = "jdbc:mysql:///roadmap_data";
        String username = "root";
        String password = "123456";
        String moduleName = "sys";
        String mapperLocation = "D:\\aGuoJ\\project\\roadmap\\roadmap-backend\\src\\main\\resources\\mapper\\" + moduleName;
        String tables = "user, user_activity, user_stats, user_question";  // TODO表名 后面添加其他新表
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("GJJ") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式  注解
                            //.fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\aGuoJ\\project\\roadmap\\roadmap-backend\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.roam") // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // 设置需要生成的表名
                            .addTablePrefix(""); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
