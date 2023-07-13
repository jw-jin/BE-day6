package com.example.beday6.repository;

import com.example.beday6.domain.version.Version;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VersionRepository extends JpaRepository<Version, Long> {
    Optional<Version> findTopByOsInfoAndServiceNameOrderByServiceVersionDesc(String osInfo, String serviceName);
    long count();
    List<Version> findByOsInfoAndServiceName(String osInfo, String serviceName);
    List<Version> findByOsInfoAndServiceNameAndIsDeleteFalse(String osInfo, String serviceName);
    Optional<Version> findByIsDelete(String isDelete);
    Page<Version> findByIsDeleteFalse(Pageable pageable);

}
