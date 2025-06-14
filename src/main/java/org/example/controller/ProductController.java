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
        return "products/save";
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

    @GetMapping("/create")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new SimpleProduct(
                0l,
                "2025.04.01",
                "Enter the product name!",
                "B",
                55,
                4500.0));
        return "products/save";
    }

    @PostMapping("/create")
    public String addProduct(Model model, @ModelAttribute("product") SimpleProduct simpleProduct) {
        Product product = new SimpleProduct(
                simpleProduct.id(),
                simpleProduct.date(),
                simpleProduct.name(),
                simpleProduct.sector(),
                simpleProduct.AvailableTickets(),
                simpleProduct.price()
        );
        productRepository.saveProduct(product);
        return "redirect:/products/list";
    }
}
