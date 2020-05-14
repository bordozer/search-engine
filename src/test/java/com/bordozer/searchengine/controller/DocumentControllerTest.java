package com.bordozer.searchengine.controller;

import com.bordozer.commons.utils.FileUtils;
import com.bordozer.searchengine.dto.DocumentDto;
import com.bordozer.searchengine.dto.ImmutableDocumentDto;
import com.bordozer.searchengine.service.DocumentService;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityExistsException;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DocumentController.class)
@AutoConfigureMockMvc
//@ExtendWith({SpringExtension.class})
class DocumentControllerTest {

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
    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    void shouldGetDocumentIfExists() {
        // given
        final DocumentDto response = DocumentDto.builder()
                .key(DOC_KEY)
                .content("one two three")
                .build();
        when(documentService.findByKey(DOC_KEY)).thenReturn(response);

        // when
        mockMvc.perform(get(GET_DOC_URL)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(GET_EXISTING_DOC_EXPECTED_RESPONSE));
    }

    @Test
    @SneakyThrows
    void shouldReturnErrorIfIfDocumentDoesNotExist() {
        // given
        when(documentService.findByKey(DOC_KEY)).thenThrow(new NoSuchElementException("Document with key '1024' not found"));

        // when
        mockMvc.perform(get(GET_DOC_URL)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(GET_NOT_EXISTING_DOC_EXPECTED_RESPONSE));
    }

    @Test
    @SneakyThrows
    void shouldCreateNewDocument() {
        // given
        final ImmutableDocumentDto dto = DocumentDto.builder()
                .key(DOC_KEY)
                .content("one two three")
                .build();
        when(documentService.addNew(dto)).thenReturn(dto);

        // when
        mockMvc.perform(post(ADD_DOC_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(CREATE_NEW_DOC_REQUEST)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(CREATE_NEW_DOC_EXPECTED_RESPONSE));
    }

    @Test
    @SneakyThrows
    void shouldReturnErrorIfDocumentKeyAlreadyExists() {
        // given
        final ImmutableDocumentDto dto = DocumentDto.builder()
                .key(DOC_KEY)
                .content("one two three")
                .build();
        when(documentService.addNew(dto)).thenThrow(new EntityExistsException("Document with key '1024' already exists"));

        // when
        mockMvc.perform(post(ADD_DOC_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(CREATE_NEW_DOC_REQUEST)
        )
                .andExpect(status().isExpectationFailed())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(DOC_ALREADY_EXISTS_EXPECTED_RESPONSE));
    }

    @Test
    @SneakyThrows
    void shouldReturnErrorIfNotAllFieldsProvided() {
        // given

        // when
        mockMvc.perform(post(ADD_DOC_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(CREATE_NEW_DOC_WRONG_REQUEST)
        )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(Matchers.containsString("some of required attributes are not set [content]")));
    }

    @Test
    @SneakyThrows
    void shouldReturnErrorIfSpareFieldProvided() {
        // given

        // when
        mockMvc.perform(post(ADD_DOC_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(CREATE_NEW_DOC_WITH_SPARE_FIELD_REQUEST)
        )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(Matchers.containsString("Unrecognized field \\\"unknownField\\\"")));
    }

    @Test
    @SneakyThrows
    void shouldReturnErrorIfKeyIsTooLong() {
        // given

        // when
        mockMvc.perform(post(ADD_DOC_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(NEW_DOC_WITH_TOO_LONG_KEY_REQUEST)
        )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(Matchers.containsString("default message [key],100,0]; default message [length must be between 0 and 100]]")));
    }
}
