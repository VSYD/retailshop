package com.example.retail.discount;

import com.example.retail.entity.User;
import com.example.retail.type.UserGroup;

import java.time.LocalDate;

public class CustomerDiscount extends DiscountFlow implements Discount {

    static Short AMOUNT_OF_YEARS_FOR_CUSTOM_DISCOUNT = 2;

    static final Byte CUSTOMER_DISCOUNT = 5;

    User user;
    public CustomerDiscount(User user) {
        this.user = user;
    }

    @Override
    boolean condition() {
        LocalDate registered = user.getRegistered();
        LocalDate now  = LocalDate.now();

        return registered.plusYears(AMOUNT_OF_YEARS_FOR_CUSTOM_DISCOUNT).isBefore(now);
    }

    @Override
    Byte discount() {
        return CustomerDiscount.CUSTOMER_DISCOUNT;
    }


    public UserGroup group() {
        return UserGroup.CUSTOMER;
    }

}
