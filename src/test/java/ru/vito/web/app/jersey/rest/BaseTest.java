package ru.vito.web.app.jersey.rest;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import ru.vito.web.app.jersey.JerseyResourceConfiguration;
import ru.vito.web.app.jersey.model.dao.impl.AbstractRepository;
import ru.vito.web.app.jersey.model.entity.Operation;
import ru.vito.web.app.jersey.model.types.OperationType;

import javax.ws.rs.core.Application;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;

import static ru.vito.web.app.jersey.rest.commons.TestUtils.*;

public abstract class BaseTest extends JerseyTest {

    @Override
    protected Application configure() {
        final JerseyResourceConfiguration jerseyResourceConfiguration = new JerseyResourceConfiguration();

        return jerseyResourceConfiguration;
    }

    @Before
    public void setup() {
        AbstractRepository.data.put(FIRST_ACCOUNT_ID, new LinkedList<>(Arrays.asList(
                Operation.builder()
                        .id(1L)
                        .partnerAccountId(FIRST_ACCOUNT_ID)
                        .createdDate(LocalDateTime.now())
                        .operationType(OperationType.CREDIT)
                        .amount(ONE_HUNDRED_USD)
                        .build(),
                Operation.builder()
                        .id(2L)
                        .partnerAccountId(FIRST_ACCOUNT_ID)
                        .createdDate(LocalDateTime.now())
                        .operationType(OperationType.DEBIT)
                        .amount(TWENTY_USD)
                        .build()))
        );

        AbstractRepository.data.put(SECOND_ACCOUNT_ID, new LinkedList<>());
    }
}
