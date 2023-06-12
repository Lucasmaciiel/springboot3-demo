package com.lmg.springboot.repositories;

import com.lmg.springboot.models.ProductsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductsModel, UUID> {
}
