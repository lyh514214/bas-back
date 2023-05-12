package com.ahao.admin.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: mybatisplus配置类
 * @Author: ahao
 * @Date: 2023/4/17 11:19
 **/

@Configuration
public class MybatisPlusConfig {

    /**
     * @Description: 新增分页拦截器，并设置数据库类型为mysql
     * @return com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
    **/
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
