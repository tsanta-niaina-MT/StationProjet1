package com.tsanta.station.repository;

import com.tsanta.station.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductInterface {
    List<Product> getAllProducts() throws SQLException;
    Product getProductById(String id) throws SQLException;
    Product deleteProduct(String id) throws SQLException;
    Product updateProduct(Product product) throws SQLException;
    Product updatePartialProduct(Product product) throws SQLException;
    Product createProduct(Product product) throws SQLException;
}
