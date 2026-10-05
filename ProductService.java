package com.homewhisk.api.service;
import com.homewhisk.api.entity.Product; import com.homewhisk.api.repository.ProductRepository; import org.springframework.stereotype.Service; import java.util.*;
@Service public class ProductService { private final ProductRepository repo; public ProductService(ProductRepository repo){this.repo=repo;} public List<Product> all(){return repo.findAll();} public Product one(Long id){return repo.findById(id).orElseThrow(()->new RuntimeException("Product not found"));} }
