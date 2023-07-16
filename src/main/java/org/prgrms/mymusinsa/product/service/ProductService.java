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
        return ProductResponseDTO.of(productRepository.findProductById(productId));
    }

    public List<ProductResponseDTO> getAllProduct() {
        return productRepository.findAll()
            .stream()
            .map(ProductResponseDTO::of)
            .toList();
    }

    @Transactional
    public ProductResponseDTO createProduct(ProductCreateRequestDTO productRequestDTO) {
        Product product = Product.of(productRequestDTO);
        productRepository.insert(product);
        return ProductResponseDTO.of(product);
    }

    @Transactional
    public ProductResponseDTO updateProduct(UUID productId, ProductUpdateRequestDTO productUpdateRequestDTO) {
        Product product = productRepository.update(Product.of(productId, productUpdateRequestDTO));
        return ProductResponseDTO.of(product);
    }

    @Transactional
    public void deleteProductById(UUID productId) {
        productRepository.deleteById(productId);
    }

    public List<ProductResponseDTO> getProductByCategory(Category category) {
        return productRepository.findProductByCategory(category)
            .stream()
            .map(product -> ProductResponseDTO.of(product))
            .toList();
    }

}
