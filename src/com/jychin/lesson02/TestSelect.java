package com.jychin.lesson02;

import com.jychin.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestSelect {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();
            String sql = "select * from users where id=1";
            rs = st.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString("name"));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            JdbcUtils.release(conn,st,rs);
        }
    }
}
