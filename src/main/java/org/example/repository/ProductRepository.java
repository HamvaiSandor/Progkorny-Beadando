package org.example.repository;

import org.example.model.SimpleProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<SimpleProduct, Long> {
}
