package com.example.productservicessst.services;

import com.example.productservicessst.dtos.FakeStoreProductDto;
import com.example.productservicessst.exceptions.ProductNotFound;
import com.example.productservicessst.models.product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.productservicessst.models.Category;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate = new RestTemplate();
    @Override
    public List<product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos =  restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );
        List<product> prods = new ArrayList<>();
        for(FakeStoreProductDto ele: fakeStoreProductDtos){
            product product = new product();
            product.setId(ele.getId());
            product.setTitle(ele.getTitle());
            product.setPrice(ele.getPrice());
            product.setImageUrl(ele.getImage());
            product.setDescription(ele.getDescription());
            Category category = new Category();
            category.setName(ele.getCategory());
            product.setCategory(category);
            prods.add(product);
        }
        return prods;
    }

    public List<String> getCategory(){
        String[] catagories =  restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories",
                 String[].class
        );
        List<String> prods = new ArrayList<>();
        for(String category : catagories) {
            prods.add(category);
        }
        return prods;
    }

        public void deleteProduct(Long id){
            restTemplate.delete("https://fakestoreapi.com/products/" + id);
        }

        public void updateProduct(product product){
            FakeStoreProductDto storeProduct = new FakeStoreProductDto();
            storeProduct.setId(product.getId());
            storeProduct.setTitle(product.getTitle());
            storeProduct.setPrice(product.getPrice());
            storeProduct.setImage(product.getImageUrl());
            storeProduct.setDescription(product.getDescription());
            storeProduct.setCategory(product.getCategory().getName());

            restTemplate.put(
                    "https://fakestoreapi.com/products/" + product.getId(),
                    storeProduct
            );
            System.out.println("Product updated successfully!!!");
        }

    @Override
    public product getSingleProduct(Long id) {
//        throw new ProductNotFound();

        FakeStoreProductDto fakeStoreProductDto =  restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
        );

        if(fakeStoreProductDto ==null) throw new ProductNotFound();

        product product = new product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDto.getCategory());

        System.out.println("product fetched");
        return product;
    }
    @Override
    public FakeStoreProductDto createProduct(product product) {
        FakeStoreProductDto prod = new FakeStoreProductDto();
        prod.setId(product.getId());
        prod.setTitle(product.getTitle());
        prod.setPrice(product.getPrice());
        prod.setImage(product.getImageUrl());
        prod.setDescription(product.getDescription());
        prod.setCategory(product.getCategory().getName());

        restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                prod,
                FakeStoreProductDto.class
        );
        return prod;
    }

    public List<product> getProductByCategory(String CategoryN){
        FakeStoreProductDto[] prods = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/" + CategoryN,
                FakeStoreProductDto[].class
        );
        List<product> products = new ArrayList<>();
        for(FakeStoreProductDto ele : prods) {
            product product = new product();
            product.setId(ele.getId());
            product.setTitle(ele.getTitle());
            product.setImageUrl(ele.getImage());
            product.setPrice(ele.getPrice());
            product.setDescription(ele.getDescription());
            Category category = new Category();
            category.setName(ele.getCategory());
            product.setCategory(category);
            products.add(product);
        }
        System.out.println("All products in " + CategoryN + " category fetched successfully!!!");
        return products;
    }

    public List<product> getSortedList(){
        FakeStoreProductDto[] prods = restTemplate.getForObject(
                "https://fakestoreapi.com/products?sort=desc",
                FakeStoreProductDto[].class
        );
        List<product> products = new ArrayList<>();
        for(FakeStoreProductDto ele : prods){
            product product = new product();
            product.setId(ele.getId());
            product.setTitle(ele.getTitle());
            product.setDescription(ele.getDescription());
            product.setImageUrl(ele.getImage());
            Category categ = new Category();
            categ.setName(ele.getCategory());
            product.setCategory(categ);
            products.add(product);
        }
        return products;
    }
}
