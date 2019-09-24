package com.bordozer.searchengine.service;

import com.bordozer.searchengine.dto.DocumentDto;

public interface DocumentService {

    DocumentDto findByKey(String key);

    DocumentDto addNew(DocumentDto dto);
}
