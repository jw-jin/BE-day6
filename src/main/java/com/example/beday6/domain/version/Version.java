package com.example.beday6.domain.version;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Version {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "os_info")
    private String osInfo;

    @Column(name = "service_version")
    private String serviceVersion;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "update_type")
    private boolean updateType;

    @Column(name = "message")
    private String message;

    @Column(name = "package_info")
    private String packageInfo;

    @CreatedDate
    @Column(name = "reg_time")
    private LocalDateTime regTime;

    public Version(String osInfo, String serviceVersion, String serviceName, boolean updateType, String message, String packageInfo) {
        this.osInfo = osInfo;
        this.serviceVersion = serviceVersion;
        this.serviceName = serviceName;
        this.updateType = updateType;
        this.message = message;
        this.packageInfo = packageInfo;
    }

    public void update(String osInfo, String serviceName, boolean updateType, String message, String packageInfo) {
        this.osInfo = osInfo;
        this.serviceName = serviceName;
        this.updateType = updateType;
        this.message = message;
        this.packageInfo = packageInfo;
    }
}
