package com.bordozer.searchengine.converter;

import com.bordozer.searchengine.dto.DocumentDto;
import com.bordozer.searchengine.entity.DocumentEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public final class DocumentConverter {

    private DocumentConverter() {
    }

    public static DocumentEntity toEntity(final DocumentDto dto) {
        Objects.requireNonNull(dto, "Dto must nit be null");

        final DocumentEntity constructed = new DocumentEntity();
        constructed.setKey(dto.getKey());
        constructed.setContent(dto.getContent());
        return constructed;
    }

    public static DocumentDto toDto(final DocumentEntity entity) {
        Objects.requireNonNull(entity, "Entity must nit be null");

        return DocumentDto.builder()
                .key(entity.getKey())
                .content(entity.getContent())
                .build();
    }
}
