package com.ahao.admin;

import com.ahao.admin.utils.SnowFlakeUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: 管理系统启动类
 * @Author: ahao
 * @Date: 2023/4/13 14:52
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.ahao.admin.mapper")
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }

}
