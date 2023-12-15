package Model.ClassMethod;

import Model.domain.User;
import Util.JDBCUtil;

import java.sql.*;

/**
 * 用户数据库操作类，包含根据用户名查找用户密码和添加用户的功能。
 */
public class UserDateBase {

    /**
     * 根据用户名查找用户密码。
     *
     * @param username 要查找的用户的用户名
     * @return 包含用户信息的 User 对象，如果未找到用户，则返回一个空的 User 对象
     */
    public User findUser(String username) {
        // SQL 查询语句，通过用户名查找用户信息
        String sql = "SELECT * FROM users WHERE username=?";
        User user = new User(); // 用于存储查询结果的 User 对象

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            // 设置查询参数为用户名
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // 从结果集中提取用户信息并设置到 User 对象中
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                }
            }
            System.out.println(sql); // 打印执行的 SQL 语句

        } catch (SQLException e) {
            e.printStackTrace(); // 处理 SQL 异常
        }

        return user;
    }

    /**
     * 添加新用户。
     *
     * @param username 要添加的用户的用户名
     * @param psw      要添加的用户的密码
     * @return 如果用户添加成功，则返回 true；否则返回 false
     */
    public boolean addUser(String username, String psw) {
        // SQL 插入语句，将新用户信息插入到数据库中
        String sql = "INSERT INTO users(username, password) VALUES (?, ?)";
        boolean result = false; // 用于存储用户添加结果的布尔值

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            // 设置插入参数为用户名和密码
            pstmt.setString(1, username);
            pstmt.setString(2, psw);

            // 执行插入操作，并检查影响的行数是否为1（表示成功插入一行）
            result = pstmt.executeUpdate() == 1;

        } catch (SQLException e) {
            // 如果发生异常，并且不是由于主键重复引起的异常，则打印异常信息
            if (!e.getMessage().contains("PRIMARY")) {
                e.printStackTrace();
            }
        }

        return result;
    }
}



