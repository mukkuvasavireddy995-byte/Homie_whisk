package com.homewhisk.api.entity;
import jakarta.persistence.*; import jakarta.validation.constraints.*;
@Entity @Table(name="customers")
public class Customer {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
 @NotBlank String name; @NotBlank String phone; String email; @NotBlank String address;
 public Customer(){} public Customer(String name,String phone,String email,String address){this.name=name;this.phone=phone;this.email=email;this.address=address;}
 public Long getId(){return id;} public String getName(){return name;} public String getPhone(){return phone;} public String getEmail(){return email;} public String getAddress(){return address;}
}
