package com.example.beday6.service;


import com.example.beday6.domain.version.Version;
import com.example.beday6.repository.VersionRepository;
import com.example.beday6.web.dto.UpdateVersionRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class VersionService {
    private final VersionRepository versionRepository;

    public Version saveVersion(Version version) {
        versionRepository.save(version);
        return version;
    }
    public List<Version> findAll() {
        return versionRepository.findAll(Sort.by(Sort.Direction.DESC,"regTime"));
    }

    public Page<Version> getVersionList(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return versionRepository.findByIsDeleteFalse(pageRequest);
    }

    public Version findById(Long id) {
        return versionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 값이 없음"));
    }

    // 최신 버전 가져오기
    public Version getRecentVersion(Version version) {
        return versionRepository.findTopByIsDeleteFalseAndOsInfoOrderByServiceVersionDesc(version.getOsInfo())
                .orElseThrow(() -> new IllegalArgumentException("해당 os의 최신 버전 찾기 실패"));
    }

    public Long getVersionCount() {
        return versionRepository.count();
    }

    // update
    public Version updateById (Long id, UpdateVersionRequestDto updateRequest) {
        // 먼저, id로 조회
        Version version = versionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 값이 없음"));
        // 서비스단에서 조회된 데이터 업데이트 코드
        version.update(updateRequest.getOsInfo(), updateRequest.getServiceName(), updateRequest.getServiceVersion(), updateRequest.isUpdateType(), updateRequest.getMessage(), updateRequest.getPackageInfo());
        return version;
    }

    // delete - 직접 DB에서 삭제하지 않고 DB에 DELETE여부를 체크만 해준다
    public void deleteById (Long id) {
        Version version = versionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 값이 없음"));
        version.delete();
    }
}
