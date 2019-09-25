package com.bordozer.searchengine.service.impl;

import com.bordozer.searchengine.converter.DocumentConverter;
import com.bordozer.searchengine.dto.DocumentDto;
import com.bordozer.searchengine.repository.SearchRepository;
import com.bordozer.searchengine.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final SearchRepository searchRepository;

    @Override
    public List<DocumentDto> find(final List<String> tokens) {
        return searchRepository.findByKeyIn(tokens).stream()
                .map(DocumentConverter::toDto)
                .collect(Collectors.toList());
    }
}
