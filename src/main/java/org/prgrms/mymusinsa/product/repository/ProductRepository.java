package org.prgrms.mymusinsa.product.repository;

import org.prgrms.mymusinsa.product.domain.Category;
import org.prgrms.mymusinsa.product.domain.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    void deleteById(UUID productId);

    void decrementSalesCount(UUID productId, long salesCount);

    List<Product> findAll();

    Optional<Product> findProductById(UUID productId);

    List<Product> findProductByCategory(Category category);

    List<Product> findProductByRanking(int topRanking);

    void incrementSalesCount(UUID productId, long salesCount);

    Product insert(Product product);

    void updateById(UUID productId, Product product);

}
