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
        for(int i=0;i<8;i++) {
            Version version = Version.builder()
                    .osInfo("IOS")
                    .message("test message"+i)
                    .packageInfo("org.package")
                    .serviceVersion("1.0."+i)
                    .serviceName("service")
                    .updateType(true)
                    .build();

            Version version2 = Version.builder()
                    .osInfo("android")
                    .message("test message"+i)
                    .packageInfo("org.package")
                    .serviceVersion("1.0."+i)
                    .serviceName("service")
                    .updateType(false)
                    .build();

            versionRepository.save(version);
            versionRepository.save(version2);
        }

    }
}
