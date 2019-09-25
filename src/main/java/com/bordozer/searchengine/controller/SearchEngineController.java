package com.bordozer.searchengine.controller;

import com.bordozer.searchengine.dto.DocumentDto;
import com.bordozer.searchengine.service.SearchService;
import com.bordozer.searchengine.utils.LoggableJson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchEngineController {

    private final SearchService searchService;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DocumentDto>> search(@RequestParam(value = "token") final List<String> tokens) {
        LOGGER.info("About to search documents by tokens '{}'", LoggableJson.of(tokens));
        return ResponseEntity.ok(searchService.find(tokens));
    }
}
