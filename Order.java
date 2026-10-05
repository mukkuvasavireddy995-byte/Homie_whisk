package com.homewhisk.api.entity;
import jakarta.persistence.*; import java.time.LocalDate; import java.util.*;
@Entity @Table(name="orders")
public class Order {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
 @ManyToOne(cascade=CascadeType.ALL) Customer customer;
 LocalDate deliveryDate; double total;
 @Enumerated(EnumType.STRING) OrderStatus status=OrderStatus.PENDING;
 @OneToMany(cascade=CascadeType.ALL) List<OrderItem> items=new ArrayList<>();
 public Long getId(){return id;} public Customer getCustomer(){return customer;} public LocalDate getDeliveryDate(){return deliveryDate;} public double getTotal(){return total;} public OrderStatus getStatus(){return status;} public List<OrderItem> getItems(){return items;}
 public void setCustomer(Customer v){customer=v;} public void setDeliveryDate(LocalDate v){deliveryDate=v;} public void setTotal(double v){total=v;} public void setStatus(OrderStatus v){status=v;} public void setItems(List<OrderItem> v){items=v;}
}
