package com.bordozer.searchengine.controller;

import com.bordozer.commons.testing.endpoint.AbstractEndpointTest;
import com.bordozer.commons.testing.endpoint.EndpointTestBuilder;
import com.bordozer.commons.utils.FileUtils;
import com.bordozer.searchengine.dto.DocumentDto;
import com.bordozer.searchengine.service.SearchService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.bordozer.commons.testing.endpoint.EndpointTestBuilder.testEndpoint;
import static com.google.common.collect.Lists.newArrayList;
import static org.mockito.Mockito.when;

@WebMvcTest(SearchEngineController.class)
class SearchEngineControllerTest extends AbstractEndpointTest {

    private static final String SEARCH_URL = "/search";
    private static final String TOKEN_HTTP_PARAM = "token";

    private static final String SEARCH_EXPECTED_RESPONSE =
            FileUtils.readSystemResource("tests/SearchEngineControllerTest/search-expected-response.json");

    @MockBean
    private SearchService searchService;

    @Test
    void shouldSearchForDocument() {
        // given
        final DocumentDto dto1 = DocumentDto.builder()
                .key("KEY_1")
                .content("one two three")
                .build();
        final DocumentDto dto2 = DocumentDto.builder()
                .key("KEY_2")
                .content("three four")
                .build();
        when(searchService.find(newArrayList("three"))).thenReturn(newArrayList(dto1, dto2));

        final EndpointTestBuilder endpoint = testEndpoint(SEARCH_URL)
                .whenRequest()
                .withHttpParameter(TOKEN_HTTP_PARAM, "three")
                .thenResponseSuccessWithJsonBody(SEARCH_EXPECTED_RESPONSE);
        // when
        getTo(endpoint);
    }

    @Test
    void shouldReturnEmptyArrayIfNoDocumentsFound() {
        // given
        when(searchService.find(newArrayList("three"))).thenReturn(newArrayList());

        final EndpointTestBuilder endpoint = testEndpoint(SEARCH_URL)
                .whenRequest()
                .withHttpParameter(TOKEN_HTTP_PARAM, "three")
                .thenResponseSuccessWithJsonBody("[]");
        // when
        getTo(endpoint);
    }
}
