package com.example.productservicessst.services;

import com.example.productservicessst.dtos.FakeStoreProductDto;
import com.example.productservicessst.models.product;

import java.util.List;

public interface ProductService {
    List<product> getAllProducts();

    product getSingleProduct(Long id);

    FakeStoreProductDto createProduct(product product);
    List<String> getCategory();

    void deleteProduct(Long id);

    void updateProduct(product product);

    List<product> getProductByCategory(String CategoryN);

    public List<product> getSortedList();
}
