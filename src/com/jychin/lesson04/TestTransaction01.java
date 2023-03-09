package com.jychin.lesson04;

import com.jychin.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestTransaction01 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.getConnection();
            // 关闭数据库的自动提交
            conn.setAutoCommit(false);
            String sql1 = "update account set `money`=`money`-100 where `name`='A'";
            st = conn.prepareStatement(sql1);
            st.executeUpdate();

            String sql2= "update account set `money` = `money`+100 where `name` = 'B'";
            st = conn.prepareStatement(sql2);
            st.executeUpdate();
            conn.commit();
            System.out.println("成功了！");
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }

    }
}
