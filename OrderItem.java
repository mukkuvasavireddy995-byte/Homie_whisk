package com.homewhisk.api.entity;
import jakarta.persistence.*;
@Entity @Table(name="order_items")
public class OrderItem {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
 Long productId; int quantity; double unitPrice;
 @Column(length=1500) String customization;
 public OrderItem(){}
 public OrderItem(Long productId,int quantity,double unitPrice,String customization){this.productId=productId;this.quantity=quantity;this.unitPrice=unitPrice;this.customization=customization;}
 public Long getId(){return id;} public Long getProductId(){return productId;} public int getQuantity(){return quantity;} public double getUnitPrice(){return unitPrice;} public String getCustomization(){return customization;}
}
