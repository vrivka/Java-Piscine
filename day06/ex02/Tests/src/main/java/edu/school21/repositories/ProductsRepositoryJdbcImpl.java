package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private final DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() {
        PreparedStatement preparedState;
        List<Product> resultList = new ArrayList<>();

        try {
            preparedState = dataSource.getConnection().prepareStatement("SELECT * FROM test.product");
            ResultSet res = preparedState.executeQuery();

            while (res.next()) {
                resultList.add(
                        new Product(
                                res.getInt("id"),
                                res.getString("name"),
                                res.getInt("price")
                        )
                );
            }
            return resultList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Product> findById(Long id) {
        PreparedStatement preparedState;

        try {
            preparedState = dataSource.getConnection().prepareStatement("SELECT * FROM test.product WHERE id = ?");
            preparedState.setLong(1, id);

            ResultSet res = preparedState.executeQuery();

            if (res.next()) {
                Product product = new Product();

                product.setId(res.getInt("id"));
                product.setName(res.getString("name"));
                product.setPrice(res.getInt("price"));
                return Optional.of(product);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void update(Product product) {
        PreparedStatement preparedState;

        try {
            preparedState = dataSource.getConnection().prepareStatement("UPDATE test.product SET id = ?, name = ?, price = ? WHERE id = " + product.getId());

            preparedState.setInt(1, product.getId());
            preparedState.setString(2, product.getName());
            preparedState.setInt(3, product.getPrice());
            preparedState.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void save(Product product) {
        PreparedStatement preparedState;

        try {
            preparedState = dataSource.getConnection().prepareStatement("INSERT INTO test.product (name, price) VALUES (?, ?)");

            preparedState.setString(1, product.getName());
            preparedState.setInt(2, product.getPrice());
            preparedState.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        PreparedStatement preparedState;

        try {
            preparedState = dataSource.getConnection().prepareStatement("DELETE FROM test.product WHERE id = " + id);

            preparedState.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
