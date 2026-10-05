package com.homewhisk.api.service;
import com.homewhisk.api.entity.*; import com.homewhisk.api.repository.*; import org.springframework.stereotype.Service; import java.util.*;
@Service public class OrderService {
 private final OrderRepository orders; private final ProductRepository products;
 public OrderService(OrderRepository orders,ProductRepository products){this.orders=orders;this.products=products;}
 public List<Order> all(){return orders.findAll();}
 public Order create(Order order){
  if(order.getItems()==null||order.getItems().isEmpty()) throw new IllegalArgumentException("At least one order item is required");
  double subtotal=0;
  for(OrderItem item:order.getItems()){
   Product p=products.findById(item.getProductId()).orElseThrow(()->new IllegalArgumentException("Product not found: "+item.getProductId()));
   subtotal+=p.getPrice()*item.getQuantity();
  }
  order.setTotal(subtotal+50); order.setStatus(OrderStatus.CONFIRMED); return orders.save(order);
 }
 public Order updateStatus(Long id,OrderStatus status){Order o=orders.findById(id).orElseThrow(()->new RuntimeException("Order not found"));o.setStatus(status);return orders.save(o);}
}
