package org.example.service;

import org.example.model.SimpleProduct;
import org.example.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    private ProductRepository repository;
    private ProductServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(ProductRepository.class);
        service = new ProductServiceImpl(repository);
    }

    @Test
    void save_shouldReturnSavedProduct() {
        SimpleProduct product = new SimpleProduct();
        product.setName("Test Product");

        when(repository.save(product)).thenReturn(product);

        SimpleProduct saved = service.save(product);

        assertNotNull(saved);
        assertEquals("Test Product", saved.getName());
        verify(repository).save(product);
    }

    @Test
    void findById_shouldReturnProductOptional() {
        SimpleProduct product = new SimpleProduct();
        product.setId(1L);
        product.setName("Product 1");

        when(repository.findById(1L)).thenReturn(Optional.of(product));

        Optional<SimpleProduct> result = service.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Product 1", result.get().getName());
        verify(repository).findById(1L);
    }

    @Test
    void deleteById_shouldCallRepositoryDelete() {
        Long id = 5L;

        doNothing().when(repository).deleteById(id);

        service.deleteById(id);

        verify(repository).deleteById(id);
    }
}