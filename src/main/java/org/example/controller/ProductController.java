package org.example.controller;

import org.example.model.Product;
import org.example.model.SimpleProduct;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/index")
    public String showIndexPage() {
        return "products/index"; // Ez az index.html fájlra mutat
    }

    @GetMapping
    public String redirectToList() {
        return "redirect:/products/list";
    }

    @GetMapping("/list")
    public String getProducts(Model model) {
        List<Product> products = productRepository.findAll()
                .stream()
                .map(p -> (Product) p)
                .collect(Collectors.toList());
        model.addAttribute("products", products);
        return "products/list";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(Model model, @PathVariable Long id) {
        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("product", product);
        return "products/save";
    }

    // POST: Update Existing Book
    @PostMapping("/edit")
    public String updateProduct(@ModelAttribute SimpleProduct product) {
        productRepository.save(product);
        return "redirect:/products/list";
        // Redirect to updated /books/list after updating
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/products/list";
        // Redirect to /authors/list after deleting
    }

    @GetMapping("/create")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new SimpleProduct(
                null, // fontos, hogy null legyen, ne 0L
                "2025.04.01",
                "Enter the product name!",
                "B",
                55,
                4500.0));
        return "products/save";
    }

    @PostMapping("/create")
    public String addProduct(@ModelAttribute("product")
                                 SimpleProduct simpleProduct) {
        productRepository.save(simpleProduct); // új rekord, ha id == null
        return "redirect:/products/list";
    }

    @PostMapping("/order")
    @ResponseBody
    public ResponseEntity<String> orderProducts(
            @RequestBody
            List<Map<String, Object>> orders) {
        for (Map<String, Object> order : orders) {
            Long id = Long.valueOf(order.get("id").toString());
            int quantity = Integer.parseInt(order.get("quantity").toString());

            productRepository.findById(id).ifPresent(product -> {
                int available = product.getAvailableTickets();
                if (quantity <= available) {
                    product.setAvailableTickets(available - quantity);
                    productRepository.save(product);
                }
            });
        }
        return ResponseEntity.ok("Order processed successfully");
    }
}
