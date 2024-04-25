package com.example.productservicessst.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class product {
    private long id;
    private String title;
    private String description;
    private double price;
    private Category category;
    private String imageUrl;

}
