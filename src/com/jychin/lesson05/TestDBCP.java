package com.jychin.lesson05;

import com.jychin.lesson02.utils.JdbcUtils;
import com.jychin.lesson05.utils.JdbcUtils_DBCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDBCP{
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