package com.jychin.lesson02;

import com.jychin.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDelete {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();
            String sql = "DELETE FROM users WHERE id=4";
            int i = st.executeUpdate(sql);
            if (i>0){
                System.out.println("删除！");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JdbcUtils.release(conn,st,rs);
        }
    }
}
