package com.bordozer.searchengine.service;

import com.bordozer.searchengine.dto.DocumentDto;

public interface DocumentService {

    DocumentDto addNew(DocumentDto dto);

    DocumentDto getDocument(String key);
}
