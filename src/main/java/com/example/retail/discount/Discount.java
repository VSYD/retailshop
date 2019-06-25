package com.example.retail.discount;

import com.example.retail.type.UserGroup;

public interface Discount {

    UserGroup group();

    Byte getDiscount();
}
