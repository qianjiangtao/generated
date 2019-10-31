package com.generated.service.utils;

/**
 * mybatis generator
 *
 * @author Gent
 * @since 2019/9/29 10:57
 */
public class Generator {
    public static void main(String[] args) {
        //module
        String module = "generator";
        //package name
        String packageName = "com.generator";
        //author
        String author = "Gent";
        //database user name
        String dbUserName = "";
        //database user password
        String dbUserPassWord = "";
        //database
        String dbDatabaseName = "";
        //table name
        String tablePrefix = "";
        //database ip
        String dbIpAddress = "";
        //当前生成类所在的模块名称
        MyBatisTemplate.setProjectPath("generator-service");
        MyBatisTemplate.generator(module, tablePrefix, packageName, author, dbUserName, dbUserPassWord, dbIpAddress, dbDatabaseName);
    }
}
