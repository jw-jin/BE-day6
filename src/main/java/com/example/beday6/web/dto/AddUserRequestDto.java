package com.example.beday6.web.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequestDto {
    private String email;
    private String password;
}
