package com.bordozer.searchengine.controller;

import com.bordozer.commons.testing.endpoint.AbstractEndpointTest;
import com.bordozer.commons.testing.endpoint.EndpointTestBuilder;
import com.bordozer.commons.utils.FileUtils;
import com.bordozer.searchengine.dto.DocumentDto;
import com.bordozer.searchengine.dto.ImmutableDocumentDto;
import com.bordozer.searchengine.service.DocumentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.persistence.EntityExistsException;
import java.util.NoSuchElementException;

import static com.bordozer.commons.testing.endpoint.EndpointTestBuilder.testEndpoint;
import static org.mockito.Mockito.when;

@WebMvcTest(DocumentController.class)
class DocumentControllerTest extends AbstractEndpointTest {

    private static final String DOC_KEY = "1024";

    private static final String GET_DOC_URL = "/doc/" + DOC_KEY;
    private static final String ADD_DOC_URL = "/doc/add";

    private static final String GET_EXISTING_DOC_EXPECTED_RESPONSE =
            FileUtils.readSystemResource("tests/DocumentControllerTest/get-existing-doc-expected-response.json");
    private static final String GET_NOT_EXISTING_DOC_EXPECTED_RESPONSE =
            FileUtils.readSystemResource("tests/DocumentControllerTest/get-not-existing-doc-expected-response.json");
    private static final String CREATE_NEW_DOC_REQUEST =
            FileUtils.readSystemResource("tests/DocumentControllerTest/create-new-doc-request.json");
    private static final String CREATE_NEW_DOC_EXPECTED_RESPONSE =
            FileUtils.readSystemResource("tests/DocumentControllerTest/create-new-doc-expected-response.json");
    private static final String DOC_ALREADY_EXISTS_EXPECTED_RESPONSE =
            FileUtils.readSystemResource("tests/DocumentControllerTest/doc-already-exists-expected-response.json");
    private static final String CREATE_NEW_DOC_WRONG_REQUEST =
            FileUtils.readSystemResource("tests/DocumentControllerTest/create-new-doc-wrong-request.json");
    private static final String CREATE_NEW_DOC_WITH_SPARE_FIELD_REQUEST =
            FileUtils.readSystemResource("tests/DocumentControllerTest/create-new-doc-with-spare-field-request.json");
    private static final String NEW_DOC_WITH_TOO_LONG_KEY_REQUEST =
            FileUtils.readSystemResource("tests/DocumentControllerTest/new-doc-with-too-long-key-request.json");

    @MockBean
    private DocumentService documentService;

    @Test
    void shouldGetDocumentIfExists() {
        // given
        final DocumentDto response = DocumentDto.builder()
                .key(DOC_KEY)
                .content("one two three")
                .build();
        when(documentService.findByKey(DOC_KEY)).thenReturn(response);

        final EndpointTestBuilder endpoint = testEndpoint(GET_DOC_URL)
                .whenRequest()
                .thenResponseSuccessWithJsonBody(GET_EXISTING_DOC_EXPECTED_RESPONSE);
        // when
        getTo(endpoint);
    }

    @Test
    void shouldReturnErrorIfIfDocumentDoesNotExist() {
        // given
        when(documentService.findByKey(DOC_KEY)).thenThrow(new NoSuchElementException("Document with key '1024' not found"));

        final EndpointTestBuilder endpoint = testEndpoint(GET_DOC_URL)
                .whenRequest()
                .thenResponse()
                .hasContentType(MediaType.APPLICATION_JSON_UTF8)
                .hasHttpStatus(HttpStatus.UNPROCESSABLE_ENTITY)
                .hasBodyJson(GET_NOT_EXISTING_DOC_EXPECTED_RESPONSE)
                .end();
        // when
        getTo(endpoint);
    }

    @Test
    void shouldCreateNewDocument() {
        // given
        final ImmutableDocumentDto dto = DocumentDto.builder()
                .key(DOC_KEY)
                .content("one two three")
                .build();
        when(documentService.addNew(dto)).thenReturn(dto);

        final EndpointTestBuilder endpoint = testEndpoint(ADD_DOC_URL)
                .whenRequest()
                .withContentType(MediaType.APPLICATION_JSON_UTF8)
                .withBodyJson(CREATE_NEW_DOC_REQUEST)
                .thenResponseSuccessWithJsonBody(CREATE_NEW_DOC_EXPECTED_RESPONSE);
        // when
        postTo(endpoint);
    }

    @Test
    void shouldReturnErrorIfDocumentKeyAlreadyExists() {
        // given
        final ImmutableDocumentDto dto = DocumentDto.builder()
                .key(DOC_KEY)
                .content("one two three")
                .build();
        when(documentService.addNew(dto)).thenThrow(new EntityExistsException("Document with key '1024' already exists"));

        final EndpointTestBuilder endpoint = testEndpoint(ADD_DOC_URL)
                .whenRequest()
                .withContentType(MediaType.APPLICATION_JSON_UTF8)
                .withBodyJson(CREATE_NEW_DOC_REQUEST)
                .thenResponse()
                .hasContentType(MediaType.APPLICATION_JSON_UTF8)
                .hasHttpStatus(HttpStatus.EXPECTATION_FAILED)
                .hasBodyJson(DOC_ALREADY_EXISTS_EXPECTED_RESPONSE)
                .end();
        // when
        postTo(endpoint);
    }

    @Test
    void shouldReturnErrorIfNotAllFieldsProvided() {
        // given
        final EndpointTestBuilder endpoint = testEndpoint(ADD_DOC_URL)
                .whenRequest()
                .withContentType(MediaType.APPLICATION_JSON_UTF8)
                .withBodyJson(CREATE_NEW_DOC_WRONG_REQUEST)
                .thenResponse()
                .hasContentType(MediaType.APPLICATION_JSON_UTF8)
                .hasHttpStatus(HttpStatus.BAD_REQUEST)
                .hasBodyContains("some of required attributes are not set [content]")
                .end();
        // when
        postTo(endpoint);
    }

    @Test
    void shouldReturnErrorIfSpareFieldProvided() {
        // given
        final EndpointTestBuilder endpoint = testEndpoint(ADD_DOC_URL)
                .whenRequest()
                .withContentType(MediaType.APPLICATION_JSON_UTF8)
                .withBodyJson(CREATE_NEW_DOC_WITH_SPARE_FIELD_REQUEST)
                .thenResponse()
                .hasContentType(MediaType.APPLICATION_JSON_UTF8)
                .hasHttpStatus(HttpStatus.BAD_REQUEST)
                .hasBodyContains("Unrecognized field \\\"unknownField\\\"")
                .end();
        // when
        postTo(endpoint);
    }

    @Test
    void shouldReturnErrorIfKeyIsTooLong() {
        // given
        final EndpointTestBuilder endpoint = testEndpoint(ADD_DOC_URL)
                .whenRequest()
                .withContentType(MediaType.APPLICATION_JSON_UTF8)
                .withBodyJson(NEW_DOC_WITH_TOO_LONG_KEY_REQUEST)
                .thenResponse()
                .hasContentType(MediaType.APPLICATION_JSON_UTF8)
                .hasHttpStatus(HttpStatus.BAD_REQUEST)
                .hasBodyContains("default message [key],100,0]; default message [length must be between 0 and 100]]")
                .end();
        // when
        postTo(endpoint);
    }
}
