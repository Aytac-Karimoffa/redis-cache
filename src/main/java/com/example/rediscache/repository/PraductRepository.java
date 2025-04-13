package com.example.rediscache.repository;

import com.example.rediscache.entity.Product;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PraductRepository extends JpaRepository<Product, Long > {
}
