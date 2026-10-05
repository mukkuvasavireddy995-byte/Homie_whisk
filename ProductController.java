package com.homewhisk.api.controller;
import com.homewhisk.api.entity.Product; import com.homewhisk.api.service.ProductService; import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/products") @CrossOrigin(origins="*")
public class ProductController {
 private final ProductService service; public ProductController(ProductService service){this.service=service;}
 @GetMapping public List<Product> all(){return service.all();}
 @GetMapping("/{id}") public ResponseEntity<Product> one(@PathVariable Long id){try{return ResponseEntity.ok(service.one(id));}catch(RuntimeException e){return ResponseEntity.notFound().build();}}
}
