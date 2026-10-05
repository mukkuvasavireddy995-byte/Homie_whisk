package com.homewhisk.api;

import com.homewhisk.api.entity.Product;
import com.homewhisk.api.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HomeWhiskApplication {
 public static void main(String[] args){SpringApplication.run(HomeWhiskApplication.class,args);}
 @Bean CommandLineRunner seed(ProductRepository repo){
  return args -> {
   if(repo.count()==0){
    repo.save(new Product(null,"Chocolate Cake","Cakes",600,"https://images.unsplash.com/photo-1578985545062-69928b1d9587?auto=format&fit=crop&w=700&q=85","Rich, moist homemade chocolate cake."));
    repo.save(new Product(null,"Vanilla Cupcakes","Cupcakes",250,"https://images.unsplash.com/photo-1587668178277-295251f900ce?auto=format&fit=crop&w=700&q=85","Soft vanilla cupcakes."));
    repo.save(new Product(null,"Chocolate Chip Cookies","Cookies",180,"https://images.unsplash.com/photo-1499636136210-6f4ee915583e?auto=format&fit=crop&w=700&q=85","Freshly baked cookies."));
    repo.save(new Product(null,"Fudge Brownies","Brownies",220,"https://images.unsplash.com/photo-1564355808539-22fda35bed7e?auto=format&fit=crop&w=700&q=85","Fudgy homemade brownies."));
   }
  };
 }
}
