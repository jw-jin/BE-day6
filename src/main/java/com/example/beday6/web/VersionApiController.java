package com.example.beday6.web;

import com.example.beday6.domain.version.Version;
import com.example.beday6.service.VersionService;
import com.example.beday6.web.dto.AddVersionRequestDto;
import com.example.beday6.web.dto.VersionPageRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/vercontrol")
@RequiredArgsConstructor
public class VersionApiController {

    private final VersionService versionService;


    @PostMapping("/versionadd")
    public ResponseEntity<Version> versionAdd(@RequestBody AddVersionRequestDto requestDto,
                                             UriComponentsBuilder builder){
        // 컨텍스트 상대 경로 URI를 쉽게 만들게 해주는 UriComponentsBuilder를 컨트롤러 메서드의 인자로 지정
        Version version = Version.createVersion(requestDto);
        versionService.saveVersion(version);
        System.out.println("requestDto = " + requestDto.getServiceName());

        URI location = builder.path("/vercontrol/versionadd")
                .buildAndExpand(version.getId()).toUri();

        return ResponseEntity.created(location).body(version);
    }

    @GetMapping("/getconfigall")
    public ResponseEntity<List<Version>> getConfigAll() {
        return ResponseEntity.ok(versionService.findAll());
    }

    @GetMapping("/getconfigpage")
    // pageCount, count 인수로 받는거 추가하기
    public ResponseEntity<Page<Version>> getConfigPage(@RequestBody VersionPageRequestDto requestDto) {
        return ResponseEntity.ok(versionService.getVersionList(requestDto.getPageNumber(), requestDto.getPageSize()));
    }
    @PostMapping("/getrecentversion")
    public ResponseEntity<Version> getRecentVersion(@RequestBody AddVersionRequestDto requestDto) {
        Version version = Version.createVersion(requestDto);
        return ResponseEntity.ok(versionService.getRecentVersion(version));
    }
}

// 테스트 눌렀을때 os 선택 후
// os에 해당하는 최신 버전을 뽑아주는게 목표다?