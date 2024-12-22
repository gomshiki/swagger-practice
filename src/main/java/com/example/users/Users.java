package com.example.users;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Users {
    private String email;
    private String password;

    @Builder
    private Users(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
