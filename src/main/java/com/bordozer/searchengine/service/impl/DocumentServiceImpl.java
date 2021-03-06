package com.bordozer.searchengine.service.impl;

import com.bordozer.searchengine.converter.DocumentConverter;
import com.bordozer.searchengine.dto.DocumentDto;
import com.bordozer.searchengine.entity.DocumentEntity;
import com.bordozer.searchengine.repository.DocumentRepository;
import com.bordozer.searchengine.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.CheckForNull;
import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    @Override
    public DocumentDto findByKey(final String key) {
        LOGGER.info("About to get document by key '{}'", key);
        @CheckForNull final DocumentEntity document = documentRepository.findByKey(key);
        if (document == null) {
            LOGGER.error("Failed to find  document with key '{}' - document does not exist", key);
            throw new NoSuchElementException(String.format("Document with key '%s' not found", key));
        }
        return DocumentConverter.toDto(document);
    }

    @Override
    @Transactional
    public DocumentDto addNew(final DocumentDto dto) {
        LOGGER.info("About to create new document: {}", dto);

        final String key = dto.getKey();
        @CheckForNull final DocumentEntity document = documentRepository.findByKey(key);
        if (document != null) {
            LOGGER.error("Failed to create new document with key '{}' - document with the key already exists", key);
            throw new EntityExistsException(String.format("Document with key '%s' already exists", key));
        }

        final DocumentEntity constructed = DocumentConverter.toEntity(dto);
        LOGGER.info("Constructed document: {}", constructed);
        final DocumentEntity saved = documentRepository.save(constructed);
        LOGGER.info("Saved document: {}", saved);

        return DocumentConverter.toDto(saved);
    }
}
