package org.prgrms.mymusinsa.product.service;

import lombok.RequiredArgsConstructor;
import org.prgrms.mymusinsa.global.exception.ErrorCode;
import org.prgrms.mymusinsa.global.exception.GlobalCustomException;
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
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDTO getProductById(UUID productId) {
        Product product = productRepository.findProductById(productId).orElseThrow(() ->
            new GlobalCustomException(ErrorCode.DB_DATA_NOTFOUND_ERROR));
        return product.toResponseDTO();
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
    public void updateProductById(UUID productId, ProductUpdateRequestDTO productUpdateRequestDTO) {
        Product product = productRepository.findProductById(productId).orElseThrow(() ->
            new GlobalCustomException(ErrorCode.DB_DATA_NOTFOUND_ERROR));
        product.update(productUpdateRequestDTO);
        productRepository.updateById(productId, product);
    }

    @Transactional
    public void deleteProductById(UUID productId) {
        productRepository.findProductById(productId).orElseThrow(() ->
            new GlobalCustomException(ErrorCode.DB_DATA_NOTFOUND_ERROR));
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
