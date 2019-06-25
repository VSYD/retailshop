package com.example.retail.discount;

public abstract class DiscountFlow {

    abstract boolean condition();

    abstract Byte discount();

    public Byte getDiscount() {
        return condition() ? discount() : 0;
    }

}
