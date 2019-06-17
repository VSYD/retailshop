package com.example.retail.resources;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HappyWishes {

    static HappyWishes happyWishes;

    @Getter
    List<String> wishes = new ArrayList<>();

    public HappyWishes() {
        wishes.add("Be happy and the world would be simple");
        wishes.add("Earn more today");
        wishes.add("Newer mind the price");
    }


    static HappyWishes getInstance() {

        if (happyWishes == null) {
            happyWishes = new HappyWishes();
        }
        return happyWishes;

    }


    public static String getWishe() {

        return getInstance().getWishes().get(
                new Random().nextInt(happyWishes.getWishes().size() - 1));
    }


}
