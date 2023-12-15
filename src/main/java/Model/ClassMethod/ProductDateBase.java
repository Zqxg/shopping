package Model.ClassMethod;

import Model.domain.Product;
import Util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDateBase {
    // 获取所有商品列表
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM products";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                double productPrice = resultSet.getDouble("product_price");
                int productQuantity = resultSet.getInt("product_quantity");
                String productImage = resultSet.getString("product_image");

                Product product = new Product(productId, productName, productPrice, productQuantity, productImage);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(connection);
            // 关闭 preparedStatement 和 resultSet（如果需要的话）
        }

        return productList;
    }

    // 添加商品到数据库
    public void addProduct(Product product) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "INSERT INTO products (product_name, product_price, product_quantity, product_image) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setDouble(2, product.getProductPrice());
            preparedStatement.setInt(3, product.getProductQuantity());
            preparedStatement.setString(4, product.getProductImage());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(connection);
            // 关闭 preparedStatement（如果需要的话）
        }
    }

    // 减少商品数量
    public  void reduceProductQuantity(int productId, int quantity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "UPDATE products SET product_quantity = product_quantity - ? WHERE product_id = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, productId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(connection);
            // 关闭 preparedStatement（如果需要的话）
        }
    }
}
