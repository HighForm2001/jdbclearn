package com.jychin.test01;

import java.sql.*;

// first
public class JdbcFirst {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 加载驱动
        // 传统写法:
        // DriverManager.registerDriver(new Driver());
        Class.forName("com.mysql.jdbc.Driver");
        // 用户信息和url
        String url = "jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username = "root";
        String password = "1234";
        // 连接成功，数据库对象
        Connection connection = DriverManager.getConnection(url, username, password);
        // 执行sql对象
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM users";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            System.out.println("id="+resultSet.getObject("id"));
            System.out.println("id="+resultSet.getObject("name"));
            System.out.println("id="+resultSet.getObject("password"));
            System.out.println("id="+resultSet.getObject("email"));
            System.out.println("id="+resultSet.getObject("birthday"));
            System.out.println("=======================================");
        }
        resultSet.close();
        statement.close();
        connection.close();

    }
}














