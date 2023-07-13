package com.example.beday6.domain.version;


import com.example.beday6.web.dto.AddVersionRequestDto;
import com.example.beday6.web.dto.UpdateCheckRequestDto;
import com.example.beday6.web.dto.UpdateCheckResponseDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
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

    @ColumnDefault("False")
    @Column(name = "is_delete")
    private boolean isDelete;

    @Builder
    public Version(String osInfo, String serviceVersion, String serviceName, boolean updateType, String message, String packageInfo, boolean isDelete) {
        this.osInfo = osInfo;
        this.serviceVersion = serviceVersion;
        this.serviceName = serviceName;
        this.updateType = updateType;
        this.message = message;
        this.packageInfo = packageInfo;
        this.isDelete = isDelete;
    }

    @Builder
    public static Version createVersion(AddVersionRequestDto addVersionRequestDto) {
        Version version = Version.builder()
                .osInfo(addVersionRequestDto.getOsInfo())
                .serviceVersion(addVersionRequestDto.getServiceVersion())
                .serviceName(addVersionRequestDto.getServiceName())
                .updateType(addVersionRequestDto.isUpdateType())
                .message(addVersionRequestDto.getMessage())
                .packageInfo(addVersionRequestDto.getPackageInfo())
                .build();

        return version;

    }

    public void update(String osInfo, String serviceName, String serviceVersion, boolean updateType, String message, String packageInfo) {
        this.osInfo = osInfo;
        this.serviceName = serviceName;
        this.serviceVersion = serviceVersion;
        this.updateType = updateType;
        this.message = message;
        this.packageInfo = packageInfo;
    }

    public void delete() {
        this.isDelete = true;
    }
}
