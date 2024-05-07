package com.tsanta.station.repository;

import com.tsanta.station.model.Sale;

import java.sql.SQLException;
import java.util.List;

public interface SaleInterface {
    List<Sale> getAllSales() throws SQLException;
    Sale getBSaleById(String id) throws SQLException;
    Sale deleteSale(String id) throws SQLException;
    Sale updateSale(Sale sale) throws SQLException;
    Sale updatePartialSale(Sale sale) throws SQLException;
    Sale createSale(Sale sale) throws SQLException;
}
