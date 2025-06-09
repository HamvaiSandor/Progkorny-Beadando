package org.example.controller;

import org.example.repository.ProductRepository;
import org.example.model.Product;
import org.example.model.SimpleProduct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/list") // /products/list
    public String getProducts(Model model) {
        List<Product> products = productRepository.findAllProducts();
        model.addAttribute("products", products);
        return "products/list";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(Model model, @PathVariable Long id) {
        Product product = productRepository.findProduct(id);
        model.addAttribute("product", product);
        return "products/edit";
    }

    @PostMapping("/update")
    public String updateProduct(Model model, @ModelAttribute("product") SimpleProduct product) {
        Product updatedProduct = productRepository.updateProduct(product.id(), product);
        model.addAttribute("product", updatedProduct);
        return "products/edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(Model model, @PathVariable Long id) {
        productRepository.deleteProduct(id);
        List<Product> products = productRepository.findAllProducts();
        model.addAttribute("products", products);
        return "products/list";
    }
}
