package com.example.beday6.web.dto;

import com.example.beday6.domain.version.Version;
import lombok.Getter;

@Getter
public class VersionResponseDto {
    private String osInfo;
    private String serviceVersion;
    private String serviceName;
    private boolean updateType;
    private String message;
    private String packageInfo;

    public VersionResponseDto (Version version) {
        osInfo = version.getOsInfo();
        serviceVersion = version.getServiceVersion();
        serviceName = version.getServiceName();
        updateType = version.isUpdateType();
        message = version.getMessage();
        packageInfo = version.getPackageInfo();
    }
}