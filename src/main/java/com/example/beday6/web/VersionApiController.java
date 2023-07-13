package com.example.beday6.web;

import com.example.beday6.domain.version.Version;
import com.example.beday6.service.VersionService;
import com.example.beday6.web.dto.AddVersionRequestDto;
import com.example.beday6.web.dto.UpdateCheckRequestDto;
import com.example.beday6.web.dto.UpdateCheckResponseDto;
import com.example.beday6.web.dto.UpdateVersionRequestDto;
import com.example.beday6.web.dto.VersionPageRequestDto;
import com.example.beday6.web.dto.VersionResponseDto;
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

        URI location = builder.path("/vercontrol/versionadd")
                .buildAndExpand(version.getId()).toUri();

        return ResponseEntity.created(location).body(version);
    }

    @GetMapping("/getconfigall")
    public ResponseEntity<List<Version>> getConfigAll(@RequestParam(value = "pageNumber") Integer pageNumber,
                                                      @RequestParam(value = "pageSize") Integer pageSize) {
        return ResponseEntity.ok(versionService.getVersionList(pageNumber, pageSize).toList());
    }


    @PostMapping("/getrecentversion")
    public ResponseEntity<VersionResponseDto> getRecentVersion(@RequestBody AddVersionRequestDto requestDto) {
        Version version = Version.createVersion(requestDto);
        return ResponseEntity.ok(new VersionResponseDto(versionService.getRecentVersion(version)));
    }
//    @GetMapping("/getrecentversion")
//    public ResponseEntity<VersionResponseDto> getRecentVersion(@RequestParam(value = "osInfo") String serviceName,
//                                                               @RequestParam(value = "serviceVersion") String serviceVersion) {
//        return ResponseEntity.ok(new VersionResponseDto(versionService.getRecentVersion(serviceName, serviceVersion)));
//    }

    @GetMapping("/updatecheck")
    public ResponseEntity<UpdateCheckResponseDto> updateCheck(@RequestParam(value = "osInfo") String osInfo,
                                                              @RequestParam(value = "serviceVersion") String serviceVersion,
                                                              @RequestParam(value = "serviceName") String serviceName) {
        return ResponseEntity.ok(versionService.updateCheck(osInfo, serviceVersion, serviceName));
    }

    @GetMapping("/getversion/{id}")
    public ResponseEntity<VersionResponseDto> getVersion(@PathVariable Long versionId) {
        return ResponseEntity.ok(new VersionResponseDto(versionService.findById(versionId)));
    }

    // update
    @PutMapping("/versions/{id}")
    public ResponseEntity<Version> updateVersion(@PathVariable Long id, @RequestBody UpdateVersionRequestDto requestDto) {
        Version updatedVersion = versionService.updateById(id, requestDto);
        return ResponseEntity.ok().body(updatedVersion);
    }

    // delete
    @DeleteMapping("/versions/{id}")
    public ResponseEntity<Void> deleteVersion(@PathVariable Long id) {
        versionService.deleteById(id);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/getoslist")
//    public ResponseEntity<List<String>> getOsList() {
//        return ResponseEntity.ok(versionService.)
//    }
}

// 테스트 눌렀을때 os 선택 후
// os에 해당하는 최신 버전을 뽑아주는게 목표다?