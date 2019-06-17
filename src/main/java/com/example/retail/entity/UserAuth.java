package com.example.retail.entity;

import lombok.Getter;
import lombok.Setter;

public class UserAuth {

    @Getter
    @Setter
    String login;

    @Getter
    @Setter
    String password;

    @Getter
    @Setter
    String token;
}
