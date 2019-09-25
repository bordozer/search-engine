package com.bordozer.searchengine.service;

import com.bordozer.searchengine.dto.DocumentDto;

import java.util.List;

public interface SearchService {

    List<DocumentDto> find(List<String> tokens);
}
