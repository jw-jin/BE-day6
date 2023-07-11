package com.example.beday6.web;

import com.example.beday6.domain.version.Version;
import com.example.beday6.service.VersionService;
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
