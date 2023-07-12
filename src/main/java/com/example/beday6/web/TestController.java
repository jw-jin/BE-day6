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
@RequestMapping("/testapi")
@RequiredArgsConstructor
public class TestController {

    private final VersionService versionService;
    @PostMapping("/getrecentversion")
    public ResponseEntity<Version> getRecentVersion(@RequestBody AddVersionRequestDto requestDto) {
        Version version = Version.createVersion(requestDto);
        System.out.println("version = " + version);
        return ResponseEntity.ok(versionService.getRecentVersion(version));
    }
}