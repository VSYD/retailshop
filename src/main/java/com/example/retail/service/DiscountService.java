package com.example.retail.service;

import com.example.retail.entity.Bill;
import com.example.retail.entity.Product;
import com.example.retail.entity.User;

import java.util.List;

public interface DiscountService {

    Bill processDiscount(List<Product> productList, User user);

}
