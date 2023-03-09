package com.jychin.lesson03;

import com.jychin.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestUpdate {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();

            // 区别
            // 使用问号占位符代替参数
            String sql = "update users set `name`=? where  id = ?";
            st = conn.prepareStatement(sql);
            st.setInt(2,3);
            st.setString(1,"jingyangTest");
            int i = st.executeUpdate();
            if (i > 0) {
                System.out.println("更新成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
