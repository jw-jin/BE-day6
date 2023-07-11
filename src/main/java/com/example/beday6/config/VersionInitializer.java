package com.example.beday6.config;

import com.example.beday6.domain.version.Version;
import com.example.beday6.repository.VersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
@RequiredArgsConstructor
public class VersionInitializer {
    private final VersionRepository versionRepository;

    @PostConstruct
    public void init() {
        Version version1 = Version.builder()
                .osInfo("IOS")
                .message("test message")
                .packageInfo("org.package")
                .serviceVersion("1.0.0")
                .serviceName("service")
                .updateType(true)
                .build();

        Version version2 = Version.builder()
                .osInfo("android")
                .message("test message2")
                .packageInfo("org.package")
                .serviceVersion("1.0.1")
                .serviceName("service")
                .updateType(true)
                .build();

        Version version3 = Version.builder()
                .osInfo("android")
                .message("test message3")
                .packageInfo("org.package")
                .serviceVersion("1.0.3")
                .serviceName("service")
                .updateType(true)
                .build();

        Version version4 = Version.builder()
                .osInfo("android")
                .message("test message4")
                .packageInfo("org.package")
                .serviceVersion("1.0.4")
                .serviceName("service")
                .updateType(true)
                .build();
        versionRepository.save(version1);
        versionRepository.save(version2);
        versionRepository.save(version3);
        versionRepository.save(version4);
    }
}
