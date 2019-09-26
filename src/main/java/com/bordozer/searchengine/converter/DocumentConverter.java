package com.bordozer.searchengine.converter;

import com.bordozer.searchengine.dto.DocumentDto;
import com.bordozer.searchengine.entity.DocumentEntity;
import com.bordozer.searchengine.entity.DocumentTokenEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public final class DocumentConverter {

    private static final String SPACE = " ";

    private DocumentConverter() {
    }

    public static DocumentEntity toEntity(final DocumentDto dto) {
        Objects.requireNonNull(dto, "Dto must nit be null");

        final DocumentEntity documentEntity = new DocumentEntity();
        final List<DocumentTokenEntity> documentTokenEntities = Arrays.stream(dto.getContent()
                .split(SPACE))
                .filter(StringUtils::isNotBlank)
                .map(String::trim)
                .map(token -> toDocumentTokenEntity(documentEntity, token))
                .collect(Collectors.toList());
        documentEntity.setKey(dto.getKey());
        documentEntity.setContent(dto.getContent());
        documentEntity.setTokens(documentTokenEntities);
        return documentEntity;
    }

    public static DocumentDto toDto(final DocumentEntity entity) {
        Objects.requireNonNull(entity, "Entity must nit be null");

        return DocumentDto.builder()
                .key(entity.getKey())
                .content(entity.getContent())
                .build();
    }

    private static DocumentTokenEntity toDocumentTokenEntity(final DocumentEntity documentEntity, final String token) {
        final DocumentTokenEntity documentTokenEntity = new DocumentTokenEntity();
        documentTokenEntity.setDocument(documentEntity);
        documentTokenEntity.setToken(token);
        return documentTokenEntity;
    }
}
