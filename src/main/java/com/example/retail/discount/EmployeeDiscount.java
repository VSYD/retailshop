package com.example.retail.discount;

import com.example.retail.type.UserGroup;

public class EmployeeDiscount extends DiscountFlow implements Discount {

    static final Byte EMPLOYEE_DISCOUNT = 30;

    @Override
    boolean condition() {
        return true;
    }

    @Override
    Byte discount() {
        return EmployeeDiscount.EMPLOYEE_DISCOUNT;
    }

    public UserGroup group() {
        return UserGroup.EMPLOYEE;
    }
}
