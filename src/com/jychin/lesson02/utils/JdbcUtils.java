package com.jychin.lesson02.utils;

import com.mysql.cj.protocol.Resultset;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {

    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    static{
        try{
            InputStream resourceAsStream = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);

            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            Class.forName(driver);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    // 获取链接
    public static Connection getConnection()throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    // 释放链接资源
    public static void release(Connection connection, Statement st, ResultSet rs){
        if (rs!=null){
            try{
                rs.close();
            }catch(SQLException ex){
                ex.printStackTrace();;
            }
        }
        if (st!= null){
            try{
                st.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        if (connection!=null){
            try{
                connection.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }





}
