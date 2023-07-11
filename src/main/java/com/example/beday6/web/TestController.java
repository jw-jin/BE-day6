package com.example.beday6.web;

import com.example.beday6.domain.version.Version;
import com.example.beday6.service.VersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/testapi")
@RequiredArgsConstructor
public class TestController {

    private final VersionService versionService;
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test page");
    }

    @GetMapping("/test2")
    public ResponseEntity<List<Version>> test2() {
        return ResponseEntity.ok(versionService.findAll());
    }
}