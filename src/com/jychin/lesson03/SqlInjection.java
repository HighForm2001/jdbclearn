package com.jychin.lesson03;

import com.jychin.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqlInjection {
    public static void main(String[] args) {
//         login("jingyang","123456"); // 正常登录
        login("'or '1=1","123456");
    }
    public static void login(String username, String password){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.getConnection();

            String sql = "select * from users where `name`=? AND `password`=?";
            st = conn.prepareStatement(sql);
            st.setString(1,username);
            st.setString(2,password);
            rs = st.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("name"));
                System.out.println(rs.getString("password"));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            JdbcUtils.release(conn,st,rs);
        }
    }
}
