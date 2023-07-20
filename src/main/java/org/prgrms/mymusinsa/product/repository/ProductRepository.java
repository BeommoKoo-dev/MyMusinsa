package org.prgrms.mymusinsa.product.repository;

import org.prgrms.mymusinsa.product.domain.Category;
import org.prgrms.mymusinsa.product.domain.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepository {

    Product insert(Product product);

    int updateById(UUID productId, Product product);

    void deleteById(UUID productId);

    List<Product> findAll();

    Product findProductById(UUID productId);

    List<Product> findProductByCategory(Category category);

    List<Product> findProductByRanking(int topRanking);

}
