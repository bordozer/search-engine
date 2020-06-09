package com.bordozer.searchengine.service.impl;

import com.bordozer.measury.stopwatcher.ThreadWatch;
import com.bordozer.searchengine.converter.DocumentConverter;
import com.bordozer.searchengine.dto.DocumentDto;
import com.bordozer.searchengine.entity.DocumentTokenEntity;
import com.bordozer.searchengine.model.SearchCriteria;
import com.bordozer.searchengine.repository.SearchRepository;
import com.bordozer.searchengine.service.SearchService;
import com.bordozer.searchengine.specification.DocumentTokenSpecification;
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
    @ThreadWatch
    public List<DocumentDto> find(final List<String> tokens) {
        final SearchCriteria searchCriteria = SearchCriteria.of(tokens);
        final DocumentTokenSpecification specification = new DocumentTokenSpecification(searchCriteria);
        return searchRepository.findAll(specification).stream()
                .map(DocumentTokenEntity::getDocument)
                .distinct()
                .map(DocumentConverter::toDto)
                .collect(Collectors.toList());
    }
}
