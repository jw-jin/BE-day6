package com.example.beday6.web.dto;


import lombok.Getter;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Getter
public class VersionPageRequestDto {
    public Integer pageNumber;
    public Integer pageSize;
}
