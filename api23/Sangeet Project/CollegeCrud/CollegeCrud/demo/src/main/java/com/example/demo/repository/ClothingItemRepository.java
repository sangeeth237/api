package com.example.demo.repository;


import com.example.demo.model.ClothingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothingItemRepository extends JpaRepository<ClothingItem, Long> {
    List<ClothingItem> findByDescriptionContainingIgnoreCase(String description);
    List<ClothingItem> findBySize(String size);
}
