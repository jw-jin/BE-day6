package com.example.beday6.service;


import com.example.beday6.domain.version.Version;
import com.example.beday6.repository.VersionRepository;
import com.example.beday6.web.dto.UpdateCheckResponseDto;
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
        System.out.println("version.getOsInfo() = " + version.getOsInfo());
        System.out.println("version.getServiceVersion() = " + version.getServiceName());
        return versionRepository.findTopByOsInfoAndServiceNameOrderByServiceVersionDesc(version.getOsInfo(), version.getServiceName())
                .orElseThrow(() -> new IllegalArgumentException("해당 os의 최신 버전 찾기 실패"));
        // 체크
    }

    public UpdateCheckResponseDto updateCheck(String osInfo, String serviceName, String serviceVersion) {
        boolean isForcedUpdate = false;
        List<Version> versionList = versionRepository.findByOsInfoAndServiceNameAndIsDeleteFalse(osInfo, serviceName);
        for (Version version : versionList) {
            if(compareVersion(serviceVersion, version.getServiceVersion())) {
                if(version.isUpdateType() == true) {
                    isForcedUpdate = true;
                    break;
                }
            }
        }
        Version recentVersion = getRecentVersion(Version.builder()
                .serviceName(serviceName)
                .osInfo(osInfo)
                        .build());
        // String serviceVersion, boolean isRecentVersion, boolean isForcedUpdate
        return new UpdateCheckResponseDto(serviceVersion, compareVersion(serviceVersion, recentVersion.getServiceVersion()), isForcedUpdate);
    }
    public Long getVersionCount() {
        return versionRepository.count();
    }



    public static boolean compareVersion(String serviceVersion, String compareVersion) {
        boolean isNeedsUpdate = false;
        String[] serviceVersionArr = serviceVersion.split(".");
        String[] compareVersionArr = compareVersion.split(".");
        int maxLen = Math.max(serviceVersionArr.length, compareVersionArr.length);
        for (int i = 0; i < maxLen; i++) {
            int x = Integer.parseInt(serviceVersionArr[i]);
            int y = Integer.parseInt(compareVersionArr[i]);
            if (x < y) {
                isNeedsUpdate = true;
            } else {
                isNeedsUpdate = false;
            }
        }
        return isNeedsUpdate;
    }
    // update
    public Version updateById(Long id, UpdateVersionRequestDto updateRequest) {
        // 먼저, id로 조회
        Version version = versionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 값이 없음"));
        // 서비스단에서 조회된 데이터 업데이트 코드
        version.update(updateRequest.getOsInfo(), updateRequest.getServiceName(), updateRequest.isUpdateType(), updateRequest.getMessage(), updateRequest.getPackageInfo());
        return version;
    }

    // delete - 직접 DB에서 삭제하지 않고 DB에 DELETE여부를 체크만 해준다
    public void deleteById (Long id) {
        Version version = versionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 값이 없음"));
        version.delete();

    }
}

