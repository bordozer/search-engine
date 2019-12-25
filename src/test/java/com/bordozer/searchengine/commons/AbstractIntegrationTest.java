package com.bordozer.searchengine.commons;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbstractIntegrationTest extends AbstractDbUnitTest {

    @LocalServerPort
    private int randomPort;

    protected int getRandomPort() {
        return randomPort;
    }
}
