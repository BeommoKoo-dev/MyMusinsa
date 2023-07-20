package org.prgrms.mymusinsa.product.repository;

import lombok.AllArgsConstructor;
import org.prgrms.mymusinsa.product.domain.Category;
import org.prgrms.mymusinsa.product.domain.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Repository
public class ProductJdbcRepository implements ProductRepository{

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO products(product_id, product_name, category," +
        "price, description, created_at, updated_at)"
        + "VALUES(UUID_TO_BIN(:productId), :productName, :category, :price, :description, :createdAt, :updatedAt)";
    private static final String UPDATE_PRODUCT_SQL = "UPDATE products SET product_name = :productName,"
        + "price = :price, description = :description, updated_at = :updatedAt, category = :category"
        + " WHERE product_id = UUID_TO_BIN(:productId)";
    private static final String SELECT_ALL_SQL = "SELECT * FROM products";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM products WHERE product_id = UUID_TO_BIN(:productId)";
    private static final String FIND_BY_CATEGORY_SQL = "SELECT * FROM products WHERE category = :category";
    private static final String FIND_BY_RANKING_SQL = "SELECT * FROM products ORDER BY sales_count DESC LIMIT :topRanking";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM products WHERE product_id = UUID_TO_BIN(:productId)";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final RowMapper<Product> productRowMapper = (resultSet, rowNumber) -> {
        UUID productId = toUUID(resultSet.getBytes("product_id"));
        String productName = resultSet.getString("product_name");
        Category category = Category.valueOf(resultSet.getString("category"));
        long price = resultSet.getLong("price");
        long salesCount = resultSet.getLong("sales_count");
        String description = resultSet.getString("description");
        LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();

        return new Product(productId, productName, category, price, salesCount, description, createdAt, updatedAt);
    };

    public Product insert(Product product) {
        jdbcTemplate.update(INSERT_PRODUCT_SQL, toMapSqlParams(product));
        return product;
    }

    public int updateById(UUID productId, Product product) {
        int update = jdbcTemplate.update(UPDATE_PRODUCT_SQL,
            toMapSqlParams(productId, product));
        return update;
    }

    public void deleteById(UUID productId) {
        jdbcTemplate.update(DELETE_BY_ID_SQL, Collections.singletonMap("productId", productId.toString()));
    }

    public List<Product> findAll() {
        return jdbcTemplate.query(SELECT_ALL_SQL, productRowMapper);
    }

    public Product findProductById(UUID productId) {
        return jdbcTemplate.queryForObject(FIND_BY_ID_SQL,
            Collections.singletonMap("productId", productId.toString()),
            productRowMapper);
    }

    public List<Product> findProductByCategory(Category category) {
        return jdbcTemplate.query(FIND_BY_CATEGORY_SQL,
            Collections.singletonMap("category", category.toString()),
            productRowMapper);
    }

    @Override
    public List<Product> findProductByRanking(int topRanking) {
        return jdbcTemplate.query(FIND_BY_RANKING_SQL,
            Collections.singletonMap("topRanking", topRanking),
            productRowMapper);
    }

    private MapSqlParameterSource toMapSqlParams(Product product) {
        return new MapSqlParameterSource().addValue("productId", product.getProductId().toString())
            .addValue("productName", product.getProductName())
            .addValue("category", product.getCategory().toString())
            .addValue("price", product.getPrice())
            .addValue("description", product.getDescription())
            .addValue("createdAt", product.getCreatedAt())
            .addValue("updatedAt", product.getUpdatedAt());
    }

    private MapSqlParameterSource toMapSqlParams(UUID productId, Product product) {
        return new MapSqlParameterSource().addValue("productId", productId.toString())
            .addValue("category", product.getCategory().toString())
            .addValue("productName", product.getProductName())
            .addValue("price", product.getPrice())
            .addValue("description", product.getDescription())
            .addValue("updatedAt", product.getUpdatedAt());
    }
    private UUID toUUID(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        return new UUID(byteBuffer.getLong(), byteBuffer.getLong());
    }

}
