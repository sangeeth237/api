package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.products;
import com.example.demo.service.ProductService;
@RestController
public class ProductController {

    @Autowired
    ProductService obj;
    @PostMapping("/post")
    public ResponseEntity<products>addProducts(@RequestBody products a){
        return new ResponseEntity<>(obj.addProducts(a),HttpStatus.CREATED);
    }

    //Getting data by id
    @GetMapping("/byid/{id}")
    public ResponseEntity<products> getProduct(@PathVariable int id) {
    products p = obj.getProduct(id);
    if (p != null) {
        return new ResponseEntity<>(p, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }

    //now to list all the data
    @GetMapping
    public ResponseEntity<List<products>>getAll(){
        return new ResponseEntity<>(obj.getAll(),HttpStatus.ACCEPTED);
    }

    //Now to update one product
    @PutMapping("/up/{id}")
    public ResponseEntity<products>updateProduct(@PathVariable int id,@RequestBody products updateProduct){
        products p=obj.ediProducts(id,updateProduct.getName(),updateProduct.getAbout());
        if(p!=null){
            return new ResponseEntity<>(p,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(p,HttpStatus.BAD_REQUEST);
        }
    }

}
