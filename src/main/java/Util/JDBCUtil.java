package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JDBCUtil {

    private static final String JDBC_URL;
    private static final String JDBC_USER;
    private static final String JDBC_PASSWORD;

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("database"); // 使用"database.properties"文件存储数据库连接信息
        JDBC_URL = resourceBundle.getString("jdbc.url");
        JDBC_USER = resourceBundle.getString("jdbc.user");
        JDBC_PASSWORD = resourceBundle.getString("jdbc.password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load JDBC driver.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        System.out.println("数据库连接成功");
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("数据库连接已关闭");
            } catch (SQLException e) {
                e.printStackTrace(); // 在实际应用中，应该处理关闭连接时的异常
            }
        }
    }
}
