package com.bordozer.searchengine.controller;

import com.bordozer.commons.testing.endpoint.AbstractEndpointTest;
import com.bordozer.commons.testing.endpoint.EndpointTestBuilder;
import com.bordozer.commons.utils.FileUtils;
import com.bordozer.searchengine.dto.DocumentDto;
import com.bordozer.searchengine.service.DocumentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.NoSuchElementException;

import static com.bordozer.commons.testing.endpoint.EndpointTestBuilder.testEndpoint;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@WebMvcTest(DocumentController.class)
class DocumentControllerTest extends AbstractEndpointTest {

    private static final String GET_DOC_URL = "/doc/1024";
    private static final String GET_EXISTING_DOC_EXPECTED_RESPONSE =
            FileUtils.readSystemResource("tests/DocumentControllerTest/get-existing-doc-expected-response.json");
    private static final String GET_NOT_EXISTING_DOC_EXPECTED_RESPONSE =
            FileUtils.readSystemResource("tests/DocumentControllerTest/get-not-existing-doc-expected-response.json");

    @MockBean
    private DocumentService documentService;

    @Test
    void shouldGetDocumentIfExists() {
        // given
        final DocumentDto response = DocumentDto.builder()
                .key("1024")
                .content("one two three")
                .build();
        when(documentService.findByKey("1024")).thenReturn(response);

        final EndpointTestBuilder endpointTest = testEndpoint(GET_DOC_URL)
                .whenRequest()
                .thenResponseSuccessWithJsonBody(GET_EXISTING_DOC_EXPECTED_RESPONSE);
        // when
        getTo(endpointTest);
    }

    @Test
    void shouldReturnErrorIfIfDocumentDoesNotExist() {
        // given
        when(documentService.findByKey("1024")).thenThrow(new NoSuchElementException("Document with key '1024' not found"));

        final EndpointTestBuilder endpointTest = testEndpoint(GET_DOC_URL)
                .whenRequest()
                .thenResponse()
                .hasContentType(MediaType.APPLICATION_JSON_UTF8)
                .hasHttpStatus(HttpStatus.UNPROCESSABLE_ENTITY)
                .hasBodyJson(GET_NOT_EXISTING_DOC_EXPECTED_RESPONSE)
                .end();
        // when
        getTo(endpointTest);
    }
}