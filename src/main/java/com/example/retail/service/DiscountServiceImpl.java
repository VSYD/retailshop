package com.example.retail.service;

import com.example.retail.entity.Bill;
import com.example.retail.entity.Product;
import com.example.retail.entity.User;
import com.example.retail.resources.HappyWishes;
import com.example.retail.type.DiscountType;
import com.example.retail.type.ProductType;
import com.example.retail.type.UserGroup;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

public class DiscountServiceImpl implements DiscountService {

    static Short AMOUNT_OF_YEARS_FOR_CUSTOM_DISCOUNT = 2;


    public Bill processDiscount(List<Product> productList, User user) {
        Bill ret = new Bill();

        UserGroup group = user.getGroup();

        switch (group) {

            case EMPLOYEE:
                ret.setDiscountPercents(DiscountType.EMPLOYEE.getDiscount());
                break;

            case AFFILIATE:
                ret.setDiscountPercents(DiscountType.AFILATE.getDiscount());
                break;

            case CUSTOMER:
                default:
                    LocalDate registered = user.getRegistered();
                    LocalDate now  = LocalDate.now();

                   if(registered.plusYears(AMOUNT_OF_YEARS_FOR_CUSTOM_DISCOUNT).isBefore(now))
                        ret.setDiscountPercents(DiscountType.TWO_EARS_CUSTOMER.getDiscount());

        }

        BigDecimal groceriesTotal = productList
                .stream()
                .filter(e -> e.getType().equals(ProductType.GROCERIES))
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        BigDecimal discountTotal = productList
                .stream()
                .filter(e -> !e.getType().equals(ProductType.GROCERIES))
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        BigDecimal discBody = discountTotal.divide(new BigDecimal("100"), 0, RoundingMode.DOWN);
        discBody = discBody.multiply(new BigDecimal(ret.getDiscountPercents()));

        discountTotal = discountTotal.subtract(discBody);

        ret.setDiscountBody(discBody);
        ret.setTotal(discountTotal.add(groceriesTotal));
        ret.setHappyWish(HappyWishes.getWishe());

        return ret;
    }

}
