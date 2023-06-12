package com.lmg.springboot.controllers;

import com.lmg.springboot.dtos.ProductRecordDTO;
import com.lmg.springboot.models.ProductsModel;
import com.lmg.springboot.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductsModel save(@RequestBody @Valid ProductRecordDTO productRecordDTO){
        var productsModel = new ProductsModel();
        BeanUtils.copyProperties(productRecordDTO, productsModel);
        return productRepository.save(productsModel);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductsModel> getAll(){
        return productRepository.findAll();
    }

}
