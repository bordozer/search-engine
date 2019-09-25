package com.bordozer.searchengine.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(of = "tokens")
public class SearchCriteria {
    private final List<String> tokens;

    public static SearchCriteria of(final List<String> tokens) {
        return new SearchCriteria(tokens);
    }
}
