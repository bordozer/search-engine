package com.bordozer.searchengine.service.impl;

import com.bordozer.searchengine.dto.DocumentDto;
import com.bordozer.searchengine.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DocumentServiceImpl implements DocumentService {

    @Override
    public DocumentDto addNew(final DocumentDto dto) {
        return DocumentDto.builder()
                .name("Mock document")
                .build();
    }

    @Override
    public DocumentDto getDocument(final String key) {
        return DocumentDto.builder()
                .name("Mock document")
                .build();
    }
}
