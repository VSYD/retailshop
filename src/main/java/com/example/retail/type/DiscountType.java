package com.example.retail.type;

public enum DiscountType {

    EMPLOYEE {
        public Byte getDiscount() {

            return 30;
        }
    },
    AFILATE {
        public Byte getDiscount() {

            return 10;
        }
    },
    TWO_EARS_CUSTOMER {
        public Byte getDiscount() {

            return 5;
        }
    };

    public abstract Byte getDiscount();

}
