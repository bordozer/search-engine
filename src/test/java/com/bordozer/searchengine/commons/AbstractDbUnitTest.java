package com.bordozer.searchengine.commons;

import com.bordozer.commons.testing.dbunit.SequenceResetterTestExecutionListener;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@SuppressWarnings("PMD.AbstractClassWithoutAbstractMethod")
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        MockitoTestExecutionListener.class,
        SequenceResetterTestExecutionListener.class
})
@Import(HsqldbTestConfiguration.class)
@DbUnitConfiguration(databaseConnection = "hsqldbDataSource")
public abstract class AbstractDbUnitTest {

    protected AbstractDbUnitTest() {
        // there is nothing here
    }
}
