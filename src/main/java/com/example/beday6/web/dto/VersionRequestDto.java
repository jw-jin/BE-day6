package com.example.beday6.web.dto;


import lombok.Getter;

@Getter
public class VersionRequestDto {
    private String osInfo;
    private String serviceVersion;
    private String serviceName;
    private boolean updateType;
    private String message;
    private String packageInfo;

}
