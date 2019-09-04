package com.mpgenertor.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.mpgenertor.config.MyAutoGenerator;

import java.io.File;
import java.io.IOException;

/**
 * @author zf1017@foxmail.com
 * @date 2019/9/4 0004 14:56
 * @description
 */
public class GeneratorCodeUtil {

    /**
     * 自动生成pojo，mapper, service, controller
     */


        /**
         * 请自定义自己的db url
         */
        private static final String DB_URL = "jdbc:mysql://localhost:3306/test?characterEncoding=utf-8&useSSL=false";

        /**
         * 请自定义自己的username
         */
        private static final String USERNAME = "root";

        /**
         * 请自定义自己的password
         */
        private static final String PASSWORD = "123456";

        private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";

        /**
         * 请自定义自己的包名，后续的代码生成会在这个包下
         */
        private static final String PACKAGE_NAME = "com.xiaofeifei.testmybatisplus";

        /**
         * 请自定义自己的模块名（可以理解为项目名称）
         */
        private static final String MODULE_NAME = "test-mybatis-plus";

        public static void main(String[] args) throws IOException {
            System.out.println("--------------------开始自动生成相关的类----------------------");
            System.out.println("args = " + new File("").getAbsolutePath() + "/src/main/java/");
            generateByTables(MODULE_NAME, "user");
            System.out.println("--------------------------生成成功------------------------");
        }

        /**
         * 通过表名生成相关类
         * @param module
         * @param tableNames
         */
        private static void generateByTables(String module, String... tableNames) {
            moduleGenerator(module, tableNames);
        }

        private static void moduleGenerator(String module, String [] tableNames){

            // 全局配置
            GlobalConfig globalConfig = getGlobalConfig(module);

            // 数据源配置
            DataSourceConfig dataSourceConfig = getDataSourceConfig();

            // 包配置
            PackageConfig packageConfig = getPackageConfig();

            // 策略配置
            StrategyConfig strategyConfig = getStrategyConfig(tableNames);

            // 模板配置
            TemplateConfig templateConfig = getTemplateConfig();

            // 采用自定义代码生成器来完成
            new MyAutoGenerator()
                    .setGlobalConfig(globalConfig)
                    .setDataSource(dataSourceConfig)
                    .setPackageInfo(packageConfig)
                    .setStrategy(strategyConfig)
                    .setTemplate(templateConfig)
                    .execute();

        }

        /**
         * 自定义代码生成模板, 由于我的项目中完全舍弃了xml文件和service接口，因此置null,
         * 在模版引擎的执行方法中会校验如果模版为空则不会执行writer()方法
         * @return
         */
        private static TemplateConfig getTemplateConfig() {
            TemplateConfig templateConfig = new TemplateConfig();

            templateConfig.setEntity("templates/entity.java.vm") // entity模板采用自定义模板
                    .setMapper("templates/mapper.java.vm")// mapper模板采用自定义模板
                    .setXml(null) // 不生成xml文件
                    .setService(null) // 不生成service接口
                    .setServiceImpl("templates/serviceImpl.java.vm") // serviceImpl模板采用自定义模板
                    .setController("templates/controller.java.vm"); // controller模板采用自定义模板
            return templateConfig;

        }

        /**
         * 定义策略
         * @param tableNames
         * @return
         */
        private static StrategyConfig getStrategyConfig(String... tableNames) {
            StrategyConfig strategyConfig = new StrategyConfig();
//        strategyConfig.setNaming()
            strategyConfig
                    .setCapitalMode(false)//驼峰命名
                    .setEntityLombokModel(true)
                    .setRestControllerStyle(true)
                    .setNaming(NamingStrategy.underline_to_camel)
                    .setColumnNaming(NamingStrategy.underline_to_camel)
                    .setInclude(tableNames);
            return strategyConfig;
        }

        /**
         * 配置生成包名
         * @return
         */
        private static PackageConfig getPackageConfig() {
            PackageConfig packageConfig = new PackageConfig();
            packageConfig.setParent(PACKAGE_NAME)
                    .setEntity("pojo")
                    .setMapper("mapper")
                    .setServiceImpl("service")
                    .setController("controller");
            return packageConfig;
        }

        /**
         * 配置数据源
         * @return
         */
        private static DataSourceConfig getDataSourceConfig() {
            String dbUrl = DB_URL;
            DataSourceConfig dataSourceConfig = new DataSourceConfig();
            dataSourceConfig.setDbType(DbType.MYSQL)
                    .setDriverName(DRIVER_NAME)
                    .setUsername(USERNAME)
                    .setPassword(PASSWORD)
                    .setUrl(dbUrl);
            return dataSourceConfig;
        }

        /**
         * 全局配置，配置生成文件的目录
         * @return
         */
        private static GlobalConfig getGlobalConfig(String module) {
            GlobalConfig globalConfig = new GlobalConfig();
            globalConfig.setOpen(false)// new File("test-mybatis-plus").getAbsolutePath()得到当前模块根目录路径
                    .setOutputDir(new File(module).getAbsolutePath() + "/src/main/java")//生成文件的输出目录
                    .setFileOverride(true)//是否覆盖已有文件
                    .setBaseResultMap(true)
                    .setBaseColumnList(true)
                    .setActiveRecord(false)
                    .setAuthor("feifei")
                    .setServiceName("%sService");
            return globalConfig;
        }
    }


