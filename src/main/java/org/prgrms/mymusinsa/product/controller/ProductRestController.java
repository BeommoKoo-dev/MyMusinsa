package org.prgrms.mymusinsa.product.controller;

import lombok.RequiredArgsConstructor;
import org.prgrms.mymusinsa.product.domain.Category;
import org.prgrms.mymusinsa.product.dto.ProductCreateRequestDTO;
import org.prgrms.mymusinsa.product.dto.ProductResponseDTO;
import org.prgrms.mymusinsa.product.dto.ProductUpdateRequestDTO;
import org.prgrms.mymusinsa.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/products")
@RequiredArgsConstructor
@RestController
public class ProductRestController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProduct() {
        List<ProductResponseDTO> allProducts = productService.getAllProduct();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping("/new")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductCreateRequestDTO productCreateRequestDTO) {
        return ResponseEntity.ok(productService.createProduct(productCreateRequestDTO));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("productId") UUID productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByCategory(@RequestParam("category") String category) {
        List<ProductResponseDTO> categoryProducts = productService.getProductByCategory(Category.valueOf(category));
        return ResponseEntity.ok(categoryProducts);
    }

    @PutMapping("/{productId}")
    public ResponseEntity updateProduct(@PathVariable("productId") UUID productId, @RequestBody ProductUpdateRequestDTO productUpdateRequestDTO) {
        productService.updateProduct(productId, productUpdateRequestDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity deleteProductById(@PathVariable("productId") UUID productId) {
        productService.deleteProductById(productId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/ranking/{topRanking}")
    public ResponseEntity<List<ProductResponseDTO>> getProductByRanking(@PathVariable("topRanking") int topRanking) {
        List<ProductResponseDTO> productsByRanking = productService.getProductByRanking(topRanking);
        return ResponseEntity.ok(productsByRanking);
    }

}
