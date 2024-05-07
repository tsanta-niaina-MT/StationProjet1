package com.tsanta.station.repository;

import java.sql.SQLException;
import java.util.List;

public interface RelationProduct {
    List<RelationProduct> getAllRelationProducts() throws SQLException;
    RelationProduct getRelationProductById(Integer id) throws SQLException;
    RelationProduct deleteRelationProduct(Integer id) throws SQLException;
    RelationProduct updaterelationProduct(RelationProduct relationProduct) throws SQLException;
    RelationProduct updatePartialRelationProduct(RelationProduct relationProduct) throws SQLException;
    RelationProduct createRelationProduct(RelationProduct relationProduct) throws SQLException;
}
