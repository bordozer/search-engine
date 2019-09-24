package com.bordozer.searchengine.service.impl;

import com.bordozer.searchengine.dto.DocumentDto;
import com.bordozer.searchengine.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DocumentServiceImpl implements DocumentService {

    @Override
    public DocumentDto addNew() {
        return DocumentDto.builder()
                .name("")
                .build();
    }
}
