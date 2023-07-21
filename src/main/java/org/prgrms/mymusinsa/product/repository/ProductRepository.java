package org.prgrms.mymusinsa.product.repository;

import org.prgrms.mymusinsa.product.domain.Category;
import org.prgrms.mymusinsa.product.domain.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    Product insert(Product product);

    void updateById(UUID productId, Product product);

    void deleteById(UUID productId);

    List<Product> findAll();

    Optional<Product> findProductById(UUID productId);

    List<Product> findProductByCategory(Category category);

    List<Product> findProductByRanking(int topRanking);

}
