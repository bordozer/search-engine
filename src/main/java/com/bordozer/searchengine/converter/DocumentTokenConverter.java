package com.bordozer.searchengine.converter;

import com.bordozer.searchengine.entity.DocumentTokenEntity;

import java.util.Objects;

public final class DocumentTokenConverter {

    private DocumentTokenConverter() {
    }

    public static DocumentTokenEntity toEntity(final String key, final String token) {
        Objects.requireNonNull(key, "Key must nit be null");
        Objects.requireNonNull(token, "Token must nit be null");

        final DocumentTokenEntity entity = new DocumentTokenEntity();
        entity.setKey(key);
        entity.setToken(token);
        return entity;
    }
}
