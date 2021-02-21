package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@AllArgsConstructor
public class UserDto {
    private Long userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String emailAddress;
    private String address;
    private String phoneNumber;

}
