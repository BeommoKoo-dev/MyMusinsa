package org.prgrms.mymusinsa.product.controller;

import lombok.AllArgsConstructor;
import org.prgrms.mymusinsa.product.dto.ProductCreateRequestDTO;
import org.prgrms.mymusinsa.product.dto.ProductResponseDTO;
import org.prgrms.mymusinsa.product.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("products")
@Controller
@AllArgsConstructor
public class ProductViewController {

    private final ProductService productService;

    @GetMapping("")
    public String getAllProducts(Model model) {
        List<ProductResponseDTO> allProducts = productService.getAllProduct();
        model.addAttribute("allProducts", allProducts);
        return "products";
    }

    @GetMapping("/new")
    public String viewNewProductPage() {
        return "new-product";
    }

    @PostMapping("/new")
    public String createProduct(ProductCreateRequestDTO productRequestDTO) {
        productService.createProduct(productRequestDTO);
        return "redirect:/products";
    }
}
