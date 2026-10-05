package com.homewhisk.api.repository;
import com.homewhisk.api.entity.Product; import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product,Long>{}
