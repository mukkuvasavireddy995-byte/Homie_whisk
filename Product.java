package com.homewhisk.api.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity @Table(name="products")
public class Product {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank private String name; @NotBlank private String category;
 @Positive private double price; @NotBlank private String image;
 @Column(length=1000) private String description;
 public Product() {}
 public Product(Long id,String name,String category,double price,String image,String description){this.id=id;this.name=name;this.category=category;this.price=price;this.image=image;this.description=description;}
 public Long getId(){return id;} public String getName(){return name;} public String getCategory(){return category;} public double getPrice(){return price;} public String getImage(){return image;} public String getDescription(){return description;}
 public void setId(Long id){this.id=id;} public void setName(String v){name=v;} public void setCategory(String v){category=v;} public void setPrice(double v){price=v;} public void setImage(String v){image=v;} public void setDescription(String v){description=v;}
}
