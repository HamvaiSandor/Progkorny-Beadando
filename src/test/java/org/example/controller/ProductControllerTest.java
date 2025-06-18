package org.example.controller;

import org.example.model.SimpleProduct;
import org.example.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // JUnit Jupiter támogatás
class ProductControllerTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    @Test
    void testGetProducts() {
        // Mock adat létrehozása
        SimpleProduct product = new SimpleProduct(1L, "2025-06-16", "Test Product", "A", 10, 4999.0);
        when(productRepository.findAll()).thenReturn(List.of(product));

        // Meghívás
        String viewName = productController.getProducts(model);

        // Ellenőrzések
        assertEquals("products/list", viewName);
        verify(model).addAttribute(eq("products"), any(List.class));
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindById_ProductExists() {
        Long productId = 1L;
        SimpleProduct product = new SimpleProduct(productId, "2025-06-16", "Existing Product", "B", 5, 3999.0);
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Meghívás
        Optional<SimpleProduct> foundProduct = productRepository.findById(productId);

        // Ellenőrzés
        assertTrue(foundProduct.isPresent());
        assertEquals("Existing Product", foundProduct.get().getName());
    }

    @Test
    void testFindById_ProductNotFound() {
        Long productId = 999L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Meghívás
        Optional<SimpleProduct> foundProduct = productRepository.findById(productId);

        // Ellenőrzés
        assertFalse(foundProduct.isPresent());
    }

    @Test
    void testDeleteProduct() {
        Long productId = 1L;
        String viewName = productController.deleteProduct(productId);

        assertEquals("redirect:/products/list", viewName);
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    void testAddProduct() {
        SimpleProduct newProduct = new SimpleProduct(null, "2025-06-16", "New Product", "C", 5, 2999.0);
        when(productRepository.save(newProduct)).thenReturn(newProduct);

        // Meghívás
        String viewName = productController.addProduct(newProduct);

        assertEquals("redirect:/products/list", viewName);
        verify(productRepository, times(1)).save(newProduct);
    }
}