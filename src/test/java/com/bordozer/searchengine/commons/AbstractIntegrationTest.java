package com.bordozer.searchengine.commons;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbstractIntegrationTest extends AbstractDbUnitTest {

    private static final String BASE_API = "http://localhost:%s";

    @LocalServerPort
    private int randomPort;

    protected String getBaseApi() {
        return String.format(BASE_API, getRandomPort());
    }

    protected int getRandomPort() {
        return randomPort;
    }
}
