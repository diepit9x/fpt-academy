package fa.training.dao;

import java.sql.SQLException;
import java.util.List;

import fa.training.entities.Product;

public interface ProductDAO {
    List<Product> getAllProducts() throws SQLException;
    Product findProductById(int productId) throws SQLException;
    boolean addProduct(Product product) throws SQLException;
    boolean updateProduct(int productId, Product product) throws SQLException;
    boolean deleteProduct(int productId) throws SQLException;
}
