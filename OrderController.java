package com.homewhisk.api.controller;
import com.homewhisk.api.entity.*; import com.homewhisk.api.service.OrderService; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/orders") @CrossOrigin(origins="*")
public class OrderController {
 private final OrderService service; public OrderController(OrderService service){this.service=service;}
 @GetMapping public List<Order> all(){return service.all();}
 @PostMapping public ResponseEntity<Order> create(@Valid @RequestBody Order order){return ResponseEntity.status(HttpStatus.CREATED).body(service.create(order));}
 @PutMapping("/{id}/status") public ResponseEntity<Order> status(@PathVariable Long id,@RequestParam OrderStatus status){return ResponseEntity.ok(service.updateStatus(id,status));}
}
