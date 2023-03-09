package com.jychin.lesson05.utils;

import com.jychin.lesson02.utils.JdbcUtils;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils_DBCP {
    private static DataSource dataSource = null;

    static{
        try{
            InputStream resourceAsStream = JdbcUtils.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);

            dataSource = BasicDataSourceFactory.createDataSource(properties);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    // 获取链接
    public static Connection getConnection()throws SQLException {
        return dataSource.getConnection();//从数据源中获取链接
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
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils_DBCP.getConnection();

            // 区别
            // 使用问号占位符代替参数
            String sql = "INSERT INTO users(id, `name`, `password`, email, birthday) \n" +
                    "VALUES(?,?,?,?,?)";
            st = conn.prepareStatement(sql);

            // 手动给参数赋值
            st.setInt(1,4);
            st.setString(2,"jingyangs");
            st.setString(3,"1234");
            st.setString(4,"jychin@gmail.com");
            // 注意点： sql.Date  数据库 java.sql.Date()
            //         util.Date Java new Date().getTime() 获得时间戳
            st.setDate(5,new java.sql.Date(new java.util.Date().getTime()));

            int i = st.executeUpdate();
            if (i > 0){
                System.out.println("插入成功！");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            JdbcUtils_DBCP.release(conn,st,rs);
        }

    }

}
