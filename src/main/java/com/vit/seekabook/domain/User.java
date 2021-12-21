package com.vit.seekabook.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String email;//Primary Key
    private String userType;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String postalCode;
}
