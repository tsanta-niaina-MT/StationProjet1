package com.tsanta.station.repository;

import com.tsanta.station.model.Product;
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
public class ProductRepository implements ProductInterface {
    private Connection connection;

    private Product newProduct(ResultSet resultSet) throws SQLException {
        return new Product(
                resultSet.getString("id_product"),
                resultSet.getString("name"),
                resultSet.getFloat("price")
        );
    }

    private String createQuery(Product product){
        StringBuilder query = new StringBuilder("SET ");
        boolean status = false;
        if(product.getName() != null){
            query.append("\"name\" = ? ");
            status = true;
        }
        if(product.getPrice() != 0){
            if(status)
                query.append(", ");
            query.append("\"price\" = ? ");
        }
        return query.toString();
    }
    @Override
    public List<Product> getAllProducts() throws SQLException {
        String query = "SELECT * FROM \"product\"";
        ResultSet resultSet = this.connection.createStatement().executeQuery(query);
        List<Product> listProducts = new ArrayList<>();
        while(resultSet.next()){
            listProducts.add(this.newProduct(resultSet));
        }
        return listProducts;
    }

    @Override
    public Product getProductById(String id) throws SQLException {
        String query = "SELECT * FROM \"Product\" WHERE \"id_product\" = ? ";
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setString(1,id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return this.newProduct(resultSet);
        }
        return null;
    }

    @Override
    public Product deleteProduct(String id) throws SQLException {
        Product product = this.getProductById(id);
        if( product != null){
            String query = "DELETE FROM \"author\" WHERE \"id_author\" = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1,id);
            statement.executeUpdate();
        }
        return product;
    }

    @Override
    public Product updateProduct(Product product) throws SQLException {
        if(this.getProductById(product.getId_product()) != null){
            String query = "UPDATE \"product\" SET \"name\" = ? , \"price\" = ? WHERE \"id_product\" = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1,product.getName());
            statement.setFloat(2,product.getPrice());
            statement.executeUpdate();
            return product;
        }
        return null;
    }

    @Override
    public Product updatePartialProduct(Product product) throws SQLException {
        if(this.getProductById(product.getId_product()) != null) {
            int valueIndex = 0;
            String query = "UPDATE \"product\" " + createQuery(product) + " WHERE \"id_product\" = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            if(product.getName() != null)
                statement.setString(++valueIndex, product.getName());
            if(product.getPrice() != 0)
                statement.setFloat(++valueIndex, product.getPrice());
            statement.setString(++valueIndex, product.getId_product());
            statement.executeUpdate();
            return this.getProductById(product.getId_product());
        }
        return null;
    }

    @Override
    public Product createProduct(Product product) throws SQLException {
        String query = "INSERT INTO \"product\"(\"name\",\"price\") VALUES (?,?)";
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setString(1,product.getName());
        statement.setFloat(2,product.getPrice());
        statement.executeUpdate();
        String queryNewAuthor = "SELECT * FROM \"author\" ORDER BY \"id_author\" DESC LIMIT 1";
        ResultSet resultSet = this.connection.createStatement().executeQuery(queryNewAuthor);
        resultSet.next();
        return this.newProduct(resultSet);
    }
}

