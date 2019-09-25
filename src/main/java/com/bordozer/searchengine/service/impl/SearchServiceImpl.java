package com.bordozer.searchengine.service.impl;

import com.bordozer.searchengine.dto.DocumentDto;
import com.bordozer.searchengine.entity.DocumentTokenEntity;
import com.bordozer.searchengine.model.SearchCriteria;
import com.bordozer.searchengine.repository.DocumentTokenSpecification;
import com.bordozer.searchengine.repository.SearchRepository;
import com.bordozer.searchengine.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final SearchRepository searchRepository;

    @Override
    public List<DocumentDto> find(final List<String> tokens) {
        final SearchCriteria searchCriteria = SearchCriteria.of(tokens);
        final DocumentTokenSpecification specification = new DocumentTokenSpecification(searchCriteria);
        final List<DocumentTokenEntity> entities = searchRepository.findAll(specification);
        return newArrayList();
    }
}
