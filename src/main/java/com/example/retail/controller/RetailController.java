package com.example.retail.controller;

import com.example.retail.entity.Bill;
import com.example.retail.entity.Product;
import com.example.retail.entity.User;
import com.example.retail.entity.UserAuth;
import com.example.retail.service.DiscountService;
import com.example.retail.type.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDate;
import java.util.List;

@RestController
public class RetailController {

    @Autowired
    DiscountService discountService;


    @RequestMapping(value = "/api/v1/login", method = RequestMethod.POST)
    public void login(UserAuth userAuth) {

        String login = userAuth.getLogin();
        String password = userAuth.getPassword();

        if("admin".equals(login) && "admin".equals(password)) {
            userAuth.setToken("1234567890");
            // TODO Encrypt and save token
        }
    }


    // TODO create filter in order to accept incoming requests by token with converting it to User entity

    @RequestMapping(value = "/api/v1/get-discount", method = RequestMethod.POST)
    public ResponseEntity<Bill> getDiscount(List<Product> productList) {

        // TODO retrieve User from storage by token

        User user = new User();
        user.setRegistered(LocalDate.MIN);
        user.setGroup(UserGroup.CUSTOMER);

        return new ResponseEntity<>(discountService
                .processDiscount(productList, user), HttpStatus.OK);

    }


}
