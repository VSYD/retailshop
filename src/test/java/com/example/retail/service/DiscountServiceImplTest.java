package com.example.retail.service;

import com.example.retail.RetailApplication;
import com.example.retail.entity.Bill;
import com.example.retail.entity.Product;
import com.example.retail.entity.User;
import com.example.retail.type.ProductType;
import com.example.retail.type.UserGroup;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest(classes = RetailApplication.class)
public class DiscountServiceImplTest {



    @Test
    public void processDiscountTwoYearsCustomerWithGroceries() {

        DiscountService ds = new DiscountServiceImpl();

        User user = new User();
        user.setGroup(UserGroup.CUSTOMER);
        user.setRegistered(LocalDate.now()
                .minusYears(DiscountServiceImpl.AMOUNT_OF_YEARS_FOR_CUSTOM_DISCOUNT)
                .minusDays(1));

        List<Product> productList = new ArrayList<>();


        productList.add(new Product("Pepsi", new BigDecimal("25.50"), ProductType.MINERAL_WATER));
        productList.add(new Product("Bread", new BigDecimal("17.75"), ProductType.BREAD));
        productList.add(new Product("White cheese", new BigDecimal("175.75"), ProductType.CHEESE));
        productList.add(new Product("Milk %2.5", new BigDecimal("101.23"), ProductType.MILK));
        productList.add(new Product("Tomato", new BigDecimal("105.46"), ProductType.GROCERIES));
        productList.add(new Product("Oil", new BigDecimal("45.46"), ProductType.GENERAL));
        productList.add(new Product("Cucumber", new BigDecimal("34.85"), ProductType.GROCERIES));

        Bill bill = ds.processDiscount(productList, user);


        assertEquals(bill.getDiscountBody(), new BigDecimal("15"));
        assertEquals(bill.getDiscountPercents(), Byte.valueOf("5"));
        assertEquals(bill.getTotal(), new BigDecimal("491.00"));

        assertNotNull(bill.getHappyWish());

    }




    @Test
    public void processDiscountAffiliateWithGroceries() {

        DiscountService ds = new DiscountServiceImpl();

        User user = new User();
        user.setGroup(UserGroup.AFFILIATE);
        user.setRegistered(LocalDate.now());

        List<Product> productList = new ArrayList<>();


        productList.add(new Product("White cheese", new BigDecimal("175.75"), ProductType.CHEESE));
        productList.add(new Product("Milk %2.5", new BigDecimal("101.23"), ProductType.MILK));
        productList.add(new Product("Tomato", new BigDecimal("105.46"), ProductType.GROCERIES));

        Bill bill = ds.processDiscount(productList, user);


        assertEquals(bill.getDiscountBody(), new BigDecimal("20"));
        assertEquals(bill.getDiscountPercents(), Byte.valueOf("10"));
        assertEquals(bill.getTotal(), new BigDecimal("362.44"));

        assertNotNull(bill.getHappyWish());
    }


    @Test
    public void processDiscountEmployeeWithGroceries() {

        DiscountService ds = new DiscountServiceImpl();

        User user = new User();
        user.setGroup(UserGroup.EMPLOYEE);
        user.setRegistered(LocalDate.now());

        List<Product> productList = new ArrayList<>();


        productList.add(new Product("Pepsi", new BigDecimal("25.50"), ProductType.MINERAL_WATER));
        productList.add(new Product("Bread", new BigDecimal("17.75"), ProductType.BREAD));
        productList.add(new Product("White cheese", new BigDecimal("175.75"), ProductType.CHEESE));
        productList.add(new Product("Milk %2.5", new BigDecimal("101.23"), ProductType.MILK));
        productList.add(new Product("Tomato", new BigDecimal("105.46"), ProductType.GROCERIES));
        productList.add(new Product("Oil", new BigDecimal("45.46"), ProductType.GENERAL));
        productList.add(new Product("Cucumber", new BigDecimal("34.85"), ProductType.GROCERIES));

        Bill bill = ds.processDiscount(productList, user);


        assertEquals(bill.getDiscountBody(), new BigDecimal("90"));
        assertEquals(bill.getDiscountPercents(), Byte.valueOf("30"));
        assertEquals(bill.getTotal(), new BigDecimal("416.00"));

        assertNotNull(bill.getHappyWish());

    }

}