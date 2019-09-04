package com.mpgenertor.config;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author zf1017@foxmail.com
 * @date 2019/9/4 0004 14:58
 * @description
 */

public class MyAutoGenerator extends AutoGenerator {

    @Override
    protected List<TableInfo> getAllTableInfoList(ConfigBuilder config) {
        List<TableInfo> allTableInfoList = super.getAllTableInfoList(config);
        allTableInfoList.forEach(tableInfo -> {
            List<TableField> fields = tableInfo.getFields();
            Set<String> importPackages = tableInfo.getImportPackages();

            fields.forEach(field -> {
                String propertyName = field.getPropertyName();
                // 将字段名称的首字母小写
                // field.setPropertyName(StringUtils.firstCharToLower(propertyName));

                // 如果存在LocalDateTime类型时，将其修改为Date类型
                if (field.getPropertyType().equals("LocalDateTime")) {
                    field.setColumnType(DbColumnType.DATE);
                    importPackages.remove("java.time.LocalDateTime");
                    importPackages.add("java.util.Date");
                }
            });

            /*
             * 将ServiceImpl名称修改为 => Service
             * ps：UserServiceImpl -> UserService
             */
            tableInfo.setServiceImplName(tableInfo.getServiceName());


            // TODO 后续需要对自动代码生成进行相关拓展，可在此进行操作
        });

        // 自定义配置模版， 如果你想添加一个新的类，可以在资源文件目录中的templates文件夹下
        // 添加自定义的模版文件
        config.setInjectionConfig(getInjectionConfig(config));

        return allTableInfoList;
    }

    public InjectionConfig getInjectionConfig(ConfigBuilder config) {
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };

        List<FileOutConfig> list = new ArrayList<>();
        FileOutConfig fileOutConfig = new FileOutConfig("templates/MyCustomTemplate.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String outputFile = String.format((config.getPathInfo().get(ConstVal.SERVICE_IMPL_PATH) + File.separator + tableInfo.getServiceImplName() + ConstVal.JAVA_SUFFIX), tableInfo.getServiceImplName());
                return outputFile;
            }
        };
        list.add(fileOutConfig);
        injectionConfig.setFileOutConfigList(list);
        return injectionConfig;
    }
}

