package com.example.retail.service;

import com.example.retail.discount.Discount;
import com.example.retail.entity.Bill;
import com.example.retail.entity.Product;
import com.example.retail.entity.User;
import com.example.retail.resources.HappyWishes;
import com.example.retail.type.ProductType;
import com.example.retail.type.UserGroup;
import org.apache.log4j.Logger;
import org.reflections.Reflections;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DiscountServiceImpl implements DiscountService {

    static Logger log = Logger.getLogger(DiscountServiceImpl.class.getName());
    static final String DISCOUNT_PACKAGE = "com.example.retail.discount";


    public Bill processDiscount(List<Product> productList, User user) {
        Bill ret = new Bill();

        UserGroup group = user.getGroup();
        Reflections reflections = new Reflections(DISCOUNT_PACKAGE);
        Set<Class<? extends Discount>> allClasses = reflections.getSubTypesOf(Discount.class);
        allClasses.forEach(e -> {
                    try {
                        Discount discount = e.newInstance();

                        Optional.ofNullable(discount.group()).ifPresent(t -> {
                                    if (t.equals(group)) {
                                        ret.setDiscountPercents(discount.getDiscount());
                                    }
                                }
                        );

                    } catch (InstantiationException | IllegalAccessException ex) {
                        log.error("Error #1 - Reflection error for Discount class");
                    }
                }
        );

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
