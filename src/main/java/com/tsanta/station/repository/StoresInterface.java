package com.tsanta.station.repository;

import com.tsanta.station.model.Stores;
import org.apache.catalina.Store;

import java.sql.SQLException;
import java.util.List;

public interface StoresInterface {
    List<Stores> getAllStores() throws SQLException;
    Stores getStoresById(String id) throws SQLException;
    Stores deleteStores(String id) throws SQLException;
    Stores updateStores(Stores stores) throws SQLException;
    Stores updatePartialStores(Stores stores) throws SQLException;
    Stores createStores(Stores stores) throws SQLException;
}
