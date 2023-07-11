package com.example.beday6.web;

import com.example.beday6.domain.version.Version;
import com.example.beday6.service.VersionService;
import com.example.beday6.web.dto.VersionRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/vercontrol")
@RequiredArgsConstructor
public class VersionApiController {

    private final VersionService versionService;


    @PostMapping("/versionadd")
    public ResponseEntity<String> versionAdd(@RequestBody VersionRequestDto requestDto){
        versionService.saveVersion(Version.builder()
                        .serviceVersion(requestDto.getServiceVersion())
                        .packageInfo(requestDto.getPackageInfo())
                        .serviceName(requestDto.getServiceName())
                        .updateType(requestDto.isUpdateType())
                        .osInfo(requestDto.getOsInfo())
                        .message(requestDto.getMessage())
                .build());

        return ResponseEntity.ok("성공");
    }

    @GetMapping(value = {"/getconfigall/{count}", "/getconfigall"})
    public ResponseEntity<List<Version>> getConfigAll(@PathVariable(required = false) Integer count) {
        if(count == null) {
            return ResponseEntity.ok(versionService.findAll());
        }
        else {
            return ResponseEntity.ok(versionService.getVersionList(count).toList());
        }
    }
}
