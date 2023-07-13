package com.example.beday6.web.dto;


import lombok.*;

@Getter
public class UpdateCheckResponseDto {
    private String recentVersion;
    private boolean isRecentVersion;
    private boolean isForcedUpdate;

    public UpdateCheckResponseDto(String recentVersion, boolean isRecentVersion, boolean isForcedUpdate) {
        this.recentVersion = recentVersion;
        this.isRecentVersion = isRecentVersion;
        this.isForcedUpdate = isForcedUpdate;
    }
}
