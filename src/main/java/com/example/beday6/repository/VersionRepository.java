package com.example.beday6.repository;

import com.example.beday6.domain.version.Version;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionRepository extends JpaRepository<Version, Long> {


}
