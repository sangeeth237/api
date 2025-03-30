package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clothing_items")
public class ClothingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String size;
    private double price;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
