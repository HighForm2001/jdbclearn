package com.jychin.lesson03;

import com.jychin.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestSearch {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.getConnection();
            String sql = "select * from users where id = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1,1);
            rs = st.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name"));
                System.out.println(rs.getInt("id"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}
