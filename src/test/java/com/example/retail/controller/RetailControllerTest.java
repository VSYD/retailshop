package com.example.retail.controller;

import com.example.retail.entity.Bill;
import com.example.retail.entity.Product;
import com.example.retail.entity.UserAuth;
import com.example.retail.type.ProductType;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RetailControllerTest {


    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }


    @Test
    public void login() {

        UserAuth userAuth = new UserAuth();
        userAuth.setPassword("admin");
        userAuth.setLogin("admin");

        HttpEntity<UserAuth> entity = new HttpEntity<>(userAuth, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/login"), HttpMethod.POST, entity, String.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void getDiscount() {

        List<Product> productList = new ArrayList<>();
        Product product = new Product("Pepsi", new BigDecimal("10.00"), ProductType.MINERAL_WATER);
        productList.add(product);

        HttpEntity<List<Product>> entity = new HttpEntity<>(productList, headers);
        ResponseEntity<Bill> response = restTemplate.postForEntity(
                createURLWithPort("/api/v1/get-discount"), entity, Bill.class);

        Bill bill = response.getBody();
        assert bill != null;
        assertEquals(bill.getTotal(), new BigDecimal("10.00"));
    }
}