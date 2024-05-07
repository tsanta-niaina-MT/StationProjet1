package com.tsanta.station.repository;

import com.tsanta.station.model.Type_movement;

import java.sql.SQLException;
import java.util.List;

public interface TypeMovementInterface {
    List<Type_movement> getAllType_movement() throws SQLException;
    Type_movement getType_movementById(String id) throws SQLException;
    Type_movement deleteType_movement(String id) throws SQLException;
    Type_movement updateType_movement(Type_movement typeMovement) throws SQLException;
    Type_movement updatePartialType_movement(Type_movement typeMovement) throws SQLException;
    Type_movement createType_movement(Type_movement typeMovement) throws SQLException;
}
