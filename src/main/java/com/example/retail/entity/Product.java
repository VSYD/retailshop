package com.example.retail.entity;

import com.example.retail.type.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
public class Product {

    @Getter
    @Setter
    String title;

    @Getter
    @Setter
    BigDecimal price;

    @Getter
    @Setter
    ProductType type;
}
