package com.bordozer.searchengine.service.impl;

import com.bordozer.searchengine.converter.DocumentConverter;
import com.bordozer.searchengine.dto.DocumentDto;
import com.bordozer.searchengine.entity.DocumentEntity;
import com.bordozer.searchengine.repository.DocumentRepository;
import com.bordozer.searchengine.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    @Override
    public DocumentDto findByKey(final String key) {
        LOGGER.info("About to get document by key '{}'", key);
        return DocumentConverter.toDto(documentRepository.findByKey(key));
    }

    @Override
    public DocumentDto addNew(final DocumentDto dto) {
        LOGGER.info("About to create new document: {}", dto);

        final DocumentEntity constructed = DocumentConverter.toEntity(dto);
        LOGGER.info("Created document: {}", constructed);
        final DocumentEntity saved = documentRepository.save(constructed);
        LOGGER.info("Saved document: {}", saved);

        return DocumentConverter.toDto(saved);
    }
}
