package com.lmg.springboot.controllers;

import com.lmg.springboot.dtos.ProductRecordDTO;
import com.lmg.springboot.models.ProductModel;
import com.lmg.springboot.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductModel save(@RequestBody @Valid ProductRecordDTO productRecordDTO){
        var productsModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDTO, productsModel);
        return productRepository.save(productsModel);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductModel> getAll(){
        List<ProductModel> products = productRepository.findAll();
        if (!products.isEmpty()){
            for (ProductModel product : products) {
                UUID id = product.getId();
                product.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).getOne(id)).withSelfRel());
            }

        }
        return products;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOne(@PathVariable UUID id) {
        Optional<ProductModel> product = productRepository.findById(id);
        if (product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.ok(product.get());
    }

}
