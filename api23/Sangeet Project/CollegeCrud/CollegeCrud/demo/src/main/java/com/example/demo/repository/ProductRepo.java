package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.products;

@Repository
public interface ProductRepo extends JpaRepository<products,Integer> {

}
