package com.bordozer.searchengine.controller;

import com.bordozer.commons.utils.LoggableJson;
import com.bordozer.searchengine.dto.DocumentDto;
import com.bordozer.searchengine.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.bordozer.searchengine.config.AopConfiguration.WATCHER;

@SuppressWarnings("checkstyle:magicnumber")
@Slf4j
@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
@Api(value = "Search controller", tags = "Search for documents")
public class SearchEngineController {

    private final SearchService searchService;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search for documents by tokens", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search for documents successfully done")
    })
    public ResponseEntity<List<DocumentDto>> search(@RequestParam(value = "token") final List<String> tokens) {
        LOGGER.info("About to search documents by tokens '{}'", LoggableJson.of(tokens));
        WATCHER.reset();
        final List<DocumentDto> documents = searchService.find(tokens);
        WATCHER.buildReportMills();
        return ResponseEntity.ok(documents);
    }
}
