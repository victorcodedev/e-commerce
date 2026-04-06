package org.victor.figueiredo.ecommerce.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.victor.figueiredo.ecommerce.dtos.ProductRequestDTO;
import org.victor.figueiredo.ecommerce.models.ProductModel;
import org.victor.figueiredo.ecommerce.services.ProductService;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping()
    public ResponseEntity createProduct(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(productService.create(productRequestDTO));
    }

    @GetMapping()
    public ResponseEntity<Page<ProductModel>> getProducts(@PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getProduct(@PathVariable UUID id) {
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable UUID id, @RequestBody @Valid ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(productService.update(id, productRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable UUID id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

}
