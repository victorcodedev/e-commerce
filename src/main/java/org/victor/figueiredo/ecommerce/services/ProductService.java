package org.victor.figueiredo.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.victor.figueiredo.ecommerce.dtos.ProductRequestDTO;
import org.victor.figueiredo.ecommerce.models.ProductModel;
import org.victor.figueiredo.ecommerce.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductModel create(ProductRequestDTO productRequestDTO) {

        ProductModel productModel = new ProductModel();

        productModel.setName(productRequestDTO.name());
        productModel.setDescription(productRequestDTO.description());
        productModel.setPrice(productRequestDTO.price());
        productModel.setQuantity(productRequestDTO.quantity());

        productRepository.save(productModel);

        return productModel;
    }

    public Page<ProductModel> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Optional<ProductModel> findById(UUID id) {
        return productRepository.findById(id);
    }

    public ProductModel update(UUID id,ProductRequestDTO productRequestDTO) {

        ProductModel product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        product.setName(productRequestDTO.name());
        product.setDescription(productRequestDTO.description());
        product.setPrice(productRequestDTO.price());
        product.setQuantity(productRequestDTO.quantity());

        return productRepository.save(product);
    }

    public void delete(UUID id) {
        productRepository.deleteById(id);
    }

}
