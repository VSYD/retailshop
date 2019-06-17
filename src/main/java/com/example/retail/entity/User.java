package com.example.retail.entity;

import com.example.retail.type.UserGroup;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;



public class User {

    @Getter
    @Setter
    UserGroup group;

    @Getter
    @Setter
    LocalDate registered;
}
