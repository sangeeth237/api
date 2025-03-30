package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.products;
import com.example.demo.repository.ProductRepo;

@Service
public class ProductService {

    @Autowired
    ProductRepo obj;
    public products addProducts(products a){
        return obj.save(a);
    }

    //Now Getting the data by id
    public products getProduct(int id){
        return obj.findById(id).orElse(null);
    }

    //To list all the data
    public List<products>getAll(){
        return obj.findAll();
    }

    //Now to update an id
    public products ediProducts(int id,String name,String about){
        products p=obj.findById(id).orElse(null);
        if(p!=null){
            p.setName(name);
            p.setAbout(about);
            return obj.save(p);
        }
        else{
            return null;
        }
    }
}
