package com.generated.service.utils;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;


/**
 * mybatis generator template
 *
 * @author Gent
 * @since 2019/9/29 10:20
 */
public class MyBatisTemplate {
    private String userName;
    private String passWord;
    private String dbName;
    private String dbIpAddress;
    private static String projectPath;

    private MyBatisTemplate() {
    }

    private MyBatisTemplate(String dbUserName, String dbUserPass, String dbIpAddress, String databaseName) {
        this.userName = dbUserName;
        this.passWord = dbUserPass;
        this.dbIpAddress = dbIpAddress;
        this.dbName = databaseName;
    }

    static void setProjectPath(String projectPath) {
        MyBatisTemplate.projectPath = projectPath;
    }

    /**
     * @param module       业务域名称，目前只有market,srv,user,pub四个域
     * @param tablePrefix  要生成的实体对应的表
     * @param packageName  要生成实体存放的包
     * @param author       开发人员
     * @param dbUserName   数据库用户名
     * @param dbUserPass   数据库密码
     * @param dbIpAddress  数据库ip地址
     * @param databaseName 数据库名称
     * @return
     */
    public static MyBatisTemplate generator(String module, String tablePrefix, String packageName, String author,
                                            String dbUserName, String dbUserPass, String dbIpAddress, String databaseName) {
        MyBatisTemplate instance = new MyBatisTemplate(dbUserName, dbUserPass, dbIpAddress, databaseName);
        String[] generatorModules = {module + "-service", module + "-dao"};
        for (String gm : generatorModules) {
            instance.handler(module, tablePrefix, gm, packageName, author, generatorModules);
        }
        return instance;
    }

    private void handler(final String module, String tablePrefix, final String gm, String packageName, String author,
                         String[] generatorModules) {
        String basePath = MyBatisTemplate.class.getResource("/").getPath().replace("/target/classes/", "");

        if (gm.equals(MyBatisTemplate.projectPath)) {
            basePath = basePath.replace(MyBatisTemplate.projectPath, "").replaceFirst("/", "");
        } else {
            basePath = basePath.replace(generatorModules[0], "").replaceFirst("/", "");
        }
        String outputDir = basePath + gm + "/src/main/java";
        File file = new File(outputDir);
        if (file.exists()) {
            System.out.println(outputDir);
            AutoGenerator mpg = new AutoGenerator();
            // 全局配置
            GlobalConfig gc = new GlobalConfig();
            gc.setOutputDir(basePath + "/" + gm + "/src/main/java");
            gc.setFileOverride(true);
            gc.setActiveRecord(false);
            // XML 二级缓存
            gc.setEnableCache(false);
            // XML ResultMap
            gc.setBaseResultMap(true);
            // XML columList
            gc.setBaseColumnList(true);
            gc.setAuthor(author);
            gc.setOpen(false);
            gc.setMapperName("%sMapper");
            gc.setXmlName("%sMapper");
            gc.setServiceName("%sService");
            gc.setServiceImplName("%sServiceImpl");
            // 数据源配置
            DataSourceConfig dsc = new DataSourceConfig();
            dsc.setDbType(DbType.MYSQL);
            dsc.setTypeConvert(new MySqlTypeConvert() {
                // 自定义数据库表字段类型转换【可选】
                @Override
                public DbColumnType processTypeConvert(String fieldType) {
                    // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
                    //将改成基础类型，修复Integer 为0 出现空指针异常处理
                    return super.processTypeConvert(fieldType);
                }
            });
            dsc.setDriverName("com.mysql.jdbc.Driver");
            dsc.setUsername(userName);
            dsc.setPassword(passWord);
            dsc.setUrl("jdbc:mysql://" + dbIpAddress + ":3306/" + dbName + "?characterEncoding=utf8");
            mpg.setDataSource(dsc);
            // 策略配置
            StrategyConfig strategy = new StrategyConfig();
            // 表名生成策略
            strategy.setNaming(NamingStrategy.underline_to_camel);
            // 需要生成的表
            strategy.setInclude(tablePrefix);
            strategy.setEntityLombokModel(true);
            mpg.setStrategy(strategy);
            // 包配置
            PackageConfig pc = new PackageConfig();
            pc.setParent(packageName);
            TemplateConfig tc = new TemplateConfig();
            //service generator
            if (generatorModules[0].equalsIgnoreCase(gm)) {
                tc.setController("");
                tc.setEntity("");
                tc.setMapper("");
                tc.setXml("");
                tc.setService("/templates/service.java.vm");
                tc.setServiceImpl("/templates/serviceImpl.java.vm");
                pc.setServiceImpl("service.impl");
                pc.setEntity("model");
                pc.setMapper("dao.mapper");
                pc.setXml("");
                pc.setService("service");
            } else if (generatorModules[1].equalsIgnoreCase(gm)) {
                //dao generator
                tc.setController("");
                tc.setEntity("/templates/entity.java.vm");
                tc.setMapper("/templates/mapper.java.vm");
                tc.setXml("/templates/mapper.xml.vm");
                tc.setService("");
                tc.setServiceImpl("");
                pc.setServiceImpl("");
                pc.setEntity("model");
                pc.setMapper("dao.mapper");
                pc.setXml("dao.mapper");
                pc.setService("");
            } else {
                System.out.println("暂不支持其他类型生成,如果需要请自己扩展");
                return;
            }
            mpg.setPackageInfo(pc);
            mpg.setTemplate(tc);
            mpg.setGlobalConfig(gc);
            // 执行生成
            mpg.execute();
        }
    }

    private void mapperGenerator() {

    }
}
