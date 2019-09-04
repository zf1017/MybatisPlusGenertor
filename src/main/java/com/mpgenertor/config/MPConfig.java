package com.mpgenertor.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zf1017@foxmail.com
 * @date 2019/6/27 0027 16:39
 * @description
 */
@Configuration
public class MPConfig {
    //当前使用p6spy
    /***
     * plus 的性能优化
     * @return
     */
//    @Bean
//    public PerformanceInterceptor performanceInterceptor() {
//        PerformanceInterceptor performanceInterceptor=new PerformanceInterceptor();
//        //*<!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 -->*//*
//        performanceInterceptor.setMaxTime(1000);
//        //*<!--SQL是否格式化 默认false-->*//*
//        performanceInterceptor.setFormat(true);
//        return performanceInterceptor;
//    }

    /**
     *	 mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");

        List<ISqlParser> sqlParserList = new ArrayList<>();
        // 攻击 SQL 阻断解析器、加入解析链，防止进行全表的删除更新
        sqlParserList.add(new BlockAttackSqlParser());
        page.setSqlParserList(sqlParserList);

        return page;
    }
}
