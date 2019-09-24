package com.bordozer.searchengine.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController()
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchEngineController {

    @GetMapping(path = "/whoami", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> search() {
        return ResponseEntity.ok("OK");
    }
}
