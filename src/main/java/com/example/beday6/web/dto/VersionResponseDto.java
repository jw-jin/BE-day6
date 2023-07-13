package com.example.beday6.web.dto;

import com.example.beday6.domain.version.Version;
import lombok.Data;


@Data
public class VersionResponseDto {
    private String osInfo;
    private String serviceVersion;
    private String serviceName;
    private boolean updateType;
    private String message;
    private String packageInfo;

    public VersionResponseDto(Version version) {
        this.osInfo = version.getOsInfo();
        this.serviceVersion = version.getServiceVersion();
        this.serviceName = version.getServiceName();
        this.updateType = version.isUpdateType();
        this.message = version.getMessage();
        this.packageInfo = version.getPackageInfo();
    }
}
