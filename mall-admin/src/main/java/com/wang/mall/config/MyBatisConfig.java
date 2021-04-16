package com.wang.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis相关配置
 * Created by macro on 2019/4/8.
 */
@Configuration
@EnableTransactionManagement
//该注解其实就是在指定包下将每一个类都默认加上@mapper注解
@MapperScan({"com.wang.mall.mapper","com.wang.mall.dao"})
public class MyBatisConfig {
}
