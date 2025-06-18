package org.example.service;

import org.example.model.SimpleProduct;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SimpleProduct> findAll() {
        return repository.findAll();
    }

    @Override
    public SimpleProduct save(SimpleProduct product) {
        return repository.save(product);
    }

    @Override
    public Optional<SimpleProduct> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
