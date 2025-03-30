package com.example.demo.controller;

import com.example.demo.model.ClothingItem;
import com.example.demo.service.ClothingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clothing-items")
public class ClothingItemController 
{

    @Autowired
    private ClothingItemService clothingItemService;

    @PostMapping
    public ClothingItem createClothingItem(@RequestBody ClothingItem clothingItem) {
        return clothingItemService.createClothingItem(clothingItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClothingItem> getClothingItemById(@PathVariable Long id) {
        Optional<ClothingItem> clothingItem = clothingItemService.getClothingItemById(id);
        return clothingItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @SuppressWarnings("unchecked")
        @GetMapping
        public List<ClothingItem> getAllClothingItems() {
            return (List<ClothingItem>) clothingItemService.getAllClothingItems(null);
        }
    

    @GetMapping("/search")
    public List<ClothingItem> searchByDescription(@RequestParam String description) {
        return clothingItemService.searchByDescription(description);
    }

    @GetMapping("/size")
    public List<ClothingItem> searchBySize(@RequestParam String size) {
        return clothingItemService.searchBySize(size);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClothingItem> updateClothingItem(@PathVariable Long id, @RequestBody ClothingItem updatedItem) {
        try {
            ClothingItem clothingItem = clothingItemService.updateClothingItem(id, updatedItem);
            return ResponseEntity.ok(clothingItem);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClothingItem(@PathVariable Long id) {
        clothingItemService.deleteClothingItem(id);
        return ResponseEntity.noContent().build();
    }
}