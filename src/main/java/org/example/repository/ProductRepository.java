package org.example.repository;

import org.example.model.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAllProducts();

    Product saveProduct(Product product);

    Product findProduct(Long id);

    Product updateProduct(Long id, Product productDetails);

    void deleteProduct(Long id);
}
