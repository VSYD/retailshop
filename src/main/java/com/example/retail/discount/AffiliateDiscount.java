package com.example.retail.discount;

import com.example.retail.type.UserGroup;

public class AffiliateDiscount extends DiscountFlow implements Discount {

    static final Byte AFFILIATE_DISCOUNT = 10;

    @Override
    boolean condition() {
        return true;
    }

    @Override
    Byte discount() {
        return AffiliateDiscount.AFFILIATE_DISCOUNT;
    }

    public UserGroup group() {
        return UserGroup.AFFILIATE;
    }

}
