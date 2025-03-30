package com.example.demo.service;

import com.example.demo.model.ClothingItem;
import com.example.demo.repository.ClothingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClothingItemService {

    @Autowired
    private ClothingItemRepository clothingItemRepository;

    public ClothingItem createClothingItem(ClothingItem clothingItem) {
        return clothingItemRepository.save(clothingItem);
    }

    public Optional<ClothingItem> getClothingItemById(Long id) {
        return clothingItemRepository.findById(id);
    }

    public Page<ClothingItem> getAllClothingItems(Pageable pageable) {
        return clothingItemRepository.findAll(pageable);
    }

    public List<ClothingItem> searchByDescription(String description) {
        return clothingItemRepository.findByDescriptionContainingIgnoreCase(description);
    }

    public List<ClothingItem> searchBySize(String size) {
        return clothingItemRepository.findBySize(size);
    }

    public ClothingItem updateClothingItem(Long id, ClothingItem updatedItem) {
        return clothingItemRepository.findById(id).map(item -> {
            item.setDescription(updatedItem.getDescription());
            item.setSize(updatedItem.getSize());
            item.setPrice(updatedItem.getPrice());
            return clothingItemRepository.save(item);
        }).orElseThrow(() -> new RuntimeException("Clothing item not found"));
    }

    public void deleteClothingItem(Long id) {
        clothingItemRepository.deleteById(id);
    }
}