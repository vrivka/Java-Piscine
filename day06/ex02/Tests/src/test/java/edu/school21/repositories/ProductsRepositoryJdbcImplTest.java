package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProductsRepositoryJdbcImplTest {
    private final List<Product> EXPECTED_FIND_ALL_PRODUCTS;
    private final Product EXPECTED_FIND_BY_ID_PRODUCT;
    private final Product EXPECTED_UPDATED_PRODUCT;
    private final Product EXPECTED_SAVE_PRODUCT;
    private final Product EXPECTED_DELETE_PRODUCT;
    private ProductsRepositoryJdbcImpl repo;

    public ProductsRepositoryJdbcImplTest() {
        EXPECTED_FIND_ALL_PRODUCTS = new ArrayList<>();
        EXPECTED_FIND_BY_ID_PRODUCT = new Product(2, "banana", 89);
        EXPECTED_UPDATED_PRODUCT = new Product(4, "potato", 49);
        EXPECTED_SAVE_PRODUCT = new Product(5, "potato", 49);
        EXPECTED_DELETE_PRODUCT = new Product(0, "apple", 112);

        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(0, "apple", 112));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(1, "orange", 120));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(2, "banana", 89));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(3, "cucumber", 79));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(4, "tomato", 89));
    }

    @BeforeEach
    public void init() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("/schema.sql")
                .addScript("/data.sql")
                .build();
        repo = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    public void findByIDTest() {
        assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, repo.findById((long)EXPECTED_FIND_BY_ID_PRODUCT.getId()).orElse(null));
    }

    @Test
    public void saveTest() {
        repo.save(EXPECTED_SAVE_PRODUCT);
        assertEquals(EXPECTED_SAVE_PRODUCT,  repo.findById((long)EXPECTED_SAVE_PRODUCT.getId()).orElse(null));
    }

    @Test
    public void deleteTest() {
        repo.delete((long)EXPECTED_DELETE_PRODUCT.getId());
        assertFalse(repo.findById((long)EXPECTED_DELETE_PRODUCT.getId()).isPresent());
    }

    @Test
    public void updateTest() {
        repo.update(EXPECTED_UPDATED_PRODUCT);
        assertEquals(EXPECTED_UPDATED_PRODUCT, repo.findById((long)EXPECTED_UPDATED_PRODUCT.getId()).orElse(null));
    }

    @Test
    public void findAllTest() {
        List<Product> returnedList = repo.findAll();
        assertEquals(EXPECTED_FIND_ALL_PRODUCTS, returnedList);
    }
}
