package com.bordozer.searchengine.controller;

import com.bordozer.commons.utils.LoggableJson;
import com.bordozer.searchengine.dto.DocumentDto;
import com.bordozer.searchengine.service.DocumentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController()
@RequestMapping("/doc")
@RequiredArgsConstructor
@Api(value = "Document controller", tags = "Operations with Documents")
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping(path = "/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get document by key", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Document successfully retrieved"),
            @ApiResponse(code = 422, message = "Document cannot be found by key")
    })
    public ResponseEntity<DocumentDto> findDocumentByKey(@PathVariable final String key) {
        LOGGER.info("Get document by key '{}'", key);
        return ResponseEntity.ok(documentService.findByKey(key));
    }

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add new document to the system", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Document is successfully saved"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 417, message = "Cannot create a Document. Document with provided key already exists")
    })
    public ResponseEntity<DocumentDto> addDocument(@Valid @RequestBody final DocumentDto dto) {
        LOGGER.info("Add a new document: {}", LoggableJson.of(dto));
        return ResponseEntity.ok(documentService.addNew(dto));
    }
}
