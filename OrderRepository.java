package com.homewhisk.api.repository;
import com.homewhisk.api.entity.Order; import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderRepository extends JpaRepository<Order,Long>{}
