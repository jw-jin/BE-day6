package com.example.beday6.service;


import com.example.beday6.domain.version.Version;
import com.example.beday6.repository.VersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VersionService {
    private final VersionRepository versionRepository;

    public List<Version> findAll() {
        return versionRepository.findAll(Sort.by(Sort.Direction.DESC,"reg_time"));
    }

    public List<Version> getVersionList(Long count) {
        return versionRepository.findAll();
    }

    public Version findById(Long id) {
        return versionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 값이 없음"));
    }
}
