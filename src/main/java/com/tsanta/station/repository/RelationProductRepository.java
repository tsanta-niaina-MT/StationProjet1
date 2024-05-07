package com.tsanta.station.repository;

import com.tsanta.station.model.Product;
import com.tsanta.station.model.RelationProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class RelationProductRepository implements RelationProductInterface{
    private Connection connection;
    private RelationProduct newProduct(ResultSet resultSet) throws SQLException {
        return new RelationProduct(
                resultSet.getString("id_relation"),
                resultSet.getString("id_station"),
                resultSet.getString("id_product")
        );
    }

    private String createQuery(RelationProduct relationProduct){
        StringBuilder query = new StringBuilder("SET ");
        boolean status = false;
        if(relationProduct.getId_relation() != null){
            if(status)
                query.append(", ");
            query.append("\"id_relation\" = ? ");
            status = true;
        }
        if(relationProduct.getId_product() != null){
            query.append("\"Id_product\" = ? ");
            status = true;
        }
        if(relationProduct.getId_station() != null){
            if(status)
                query.append(", ");
            query.append("\"id_station\" = ? ");
            status = true;
        }
        return query.toString();
    }
    @Override
    public List<RelationProduct> getAllRelationProduct() throws SQLException {
        String query = "SELECT * FROM \"relationProduct\"";
        ResultSet resultSet = this.connection.createStatement().executeQuery(query);
        List<RelationProduct> listRelationProduct = new ArrayList<>();
        while(resultSet.next()){
            listRelationProduct.add(this.newProduct(resultSet));
        }
        return listRelationProduct;
    }

    @Override
    public RelationProduct getRelationProductById(String id) throws SQLException {
        String query = "SELECT * FROM \"relationProduct\" WHERE \"id_relation\" = ? ";
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setString(1,id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return this.newProduct(resultSet);
        }
        return null;
    }

    @Override
    public RelationProduct deleteRelationProduct(String id) throws SQLException {
        RelationProduct relationProduct = this.getRelationProductById(id);
        if( relationProduct != null){
            String query = "DELETE FROM \"author\" WHERE \"id_author\" = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1,id);
            statement.executeUpdate();
        }
        return relationProduct;
    }

    @Override
    public RelationProduct updateRelationProduct(RelationProduct relationProduct) throws SQLException {
        if(this.getProductById(relationProduct.getId_product()) != null){
            String query = "UPDATE \"relationProduct\" SET \"id_relation\" = ? , \"id_product\" = ? WHERE \"id_station\" = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1,relationProduct.getId_product());
            statement.setString(2,relationProduct.getId_station());
            statement.executeUpdate();
            return relationProduct;
        }
        return null;
    }

    @Override
    public RelationProduct updatePartialRelationProduct(RelationProduct relationProduct) throws SQLException {
        if(this.getProductById(relationProduct.getId_product()) != null) {
            int valueIndex = 0;
            String query = "UPDATE \"relationProduct\" " + createQuery(relationProduct) + " WHERE \"id_relation\" = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            if(relationProduct.getId_station() != null)
                statement.setString(++valueIndex, relationProduct.getId_station());
            if(relationProduct.getId_product() != null)
                statement.setString(++valueIndex, relationProduct.getId_product());
            statement.setString(++valueIndex, relationProduct.getId_product());
            statement.executeUpdate();
            return this.getRelationProductById(relationProduct.getId_product());
        }
        return null;
    }

    @Override
    public RelationProduct createRelationProduct(RelationProduct relationProduct) throws SQLException {
        String query = "INSERT INTO \"relationProduct\"(\"id_station\",\"id_product\") VALUES (?,?)";
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setString(1,relationProduct.getId_station());
        statement.setString(2,relationProduct.getId_product());
        statement.executeUpdate();
        String queryNewRelationProduct = "SELECT * FROM \"relationProduct\" ORDER BY \"id_relation\" DESC LIMIT 1";
        ResultSet resultSet = this.connection.createStatement().executeQuery(queryNewRelationProduct);
        resultSet.next();
        return this.newProduct(resultSet);
    }