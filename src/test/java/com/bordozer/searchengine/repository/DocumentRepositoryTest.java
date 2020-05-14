package com.bordozer.searchengine.repository;

import com.bordozer.searchengine.commons.AbstractDbUnitTest;
import com.bordozer.searchengine.entity.DocumentEntity;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.CheckForNull;

import static org.assertj.core.api.Assertions.assertThat;

class DocumentRepositoryTest extends AbstractDbUnitTest {

    @Autowired
    private DocumentRepository documentRepository;

    @Test
    @DatabaseSetup("/tests/DocumentRepositoryTest/initDB.xml")
    void shouldReturnNullIfUserIdNotFound() {
        @CheckForNull final DocumentEntity entity = documentRepository.findByKey("wrong key");

        assertThat(entity).isNull();
    }
}
