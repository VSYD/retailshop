package com.example.retail.entity;

import com.example.retail.type.ProductType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

public class Bill {

    @Getter
    @Setter
    List<ProductType> productList;

    @Getter
    @Setter
    BigDecimal total;

    @Getter
    @Setter
    // Not more than 100%
    Byte discountPercents;

    @Getter
    @Setter
    BigDecimal discountBody;


    @Getter
    @Setter
    String happyWish;
}
