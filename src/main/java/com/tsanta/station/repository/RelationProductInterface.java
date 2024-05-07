package com.tsanta.station.repository;

import com.tsanta.station.model.Product;
import com.tsanta.station.model.RelationProduct;

import java.sql.SQLException;
import java.util.List;

public interface RelationProductInterface {
    List<RelationProduct> getAllRelationProducts() throws SQLException;
    RelationProduct getRelationProductById(String id) throws SQLException;
    RelationProduct deleteRelationProduct(String id) throws SQLException;
    RelationProduct updaterelationProduct(RelationProduct relationProduct) throws SQLException;
    RelationProduct updatePartialRelationProduct(RelationProduct relationProduct) throws SQLException;
    RelationProduct createRelationProduct(RelationProduct relationProduct) throws SQLException;

    List<RelationProduct> getAllRelationProduct() throws SQLException;

    Product getProductById(String id) throws SQLException;

    Product deleteProduct(String id) throws SQLException;

    Product updateProduct(Product product) throws SQLException;

    RelationProduct updateRelationProduct(RelationProduct relationProduct) throws SQLException;

    Product updatePartialProduct(Product product) throws SQLException;

    Product createProduct(Product product) throws SQLException;
}
