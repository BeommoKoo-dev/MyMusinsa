package org.prgrms.mymusinsa.product.service;

import lombok.AllArgsConstructor;
import org.prgrms.mymusinsa.product.domain.Category;
import org.prgrms.mymusinsa.product.domain.Product;
import org.prgrms.mymusinsa.product.dto.ProductCreateRequestDTO;
import org.prgrms.mymusinsa.product.dto.ProductResponseDTO;
import org.prgrms.mymusinsa.product.dto.ProductUpdateRequestDTO;
import org.prgrms.mymusinsa.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDTO getProductById(UUID productId) {
        return productRepository.findProductById(productId).toResponseDTO();
    }

    public List<ProductResponseDTO> getAllProduct() {
        return productRepository.findAll()
            .stream()
            .map(Product::toResponseDTO)
            .toList();
    }

    @Transactional
    public ProductResponseDTO createProduct(ProductCreateRequestDTO productRequestDTO) {
        Product product = productRequestDTO.toProduct();
        productRepository.insert(product);
        return product.toResponseDTO();
    }

    @Transactional
    public int updateProduct(UUID productId, ProductUpdateRequestDTO productUpdateRequestDTO) {
        Product product = productUpdateRequestDTO.toProduct();
        return productRepository.updateById(productId, product);
    }

    @Transactional
    public void deleteProductById(UUID productId) {
        productRepository.deleteById(productId);
    }

    public List<ProductResponseDTO> getProductByCategory(Category category) {
        return productRepository.findProductByCategory(category)
            .stream()
            .map(Product::toResponseDTO)
            .toList();
    }

    public List<ProductResponseDTO> getProductByRanking(int topRanking) {
        return productRepository.findProductByRanking(topRanking)
            .stream()
            .map(Product::toResponseDTO)
            .toList();
    }

}
