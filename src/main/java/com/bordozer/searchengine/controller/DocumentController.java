package com.bordozer.searchengine.controller;

import com.bordozer.searchengine.dto.DocumentDto;
import com.bordozer.searchengine.service.DocumentService;
import com.bordozer.searchengine.utils.LoggableJson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController()
@RequestMapping("/doc")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping(path = "/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DocumentDto> findDocumentByKey(@RequestParam final String key) {
        LOGGER.info("Get document by key '{}'", key);
        return ResponseEntity.ok(documentService.findByKey(key));
    }

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DocumentDto> addDocument(@RequestBody final DocumentDto dto) {
        LOGGER.info("Add a new document: {}", LoggableJson.of(dto));
        return ResponseEntity.ok(documentService.addNew(dto));
    }
}
