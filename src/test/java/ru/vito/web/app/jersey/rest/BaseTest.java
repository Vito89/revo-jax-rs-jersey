package ru.vito.web.app.jersey.rest;

import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.test.JerseyTest;
import org.javamoney.moneta.Money;
import org.junit.Before;
import ru.vito.web.app.jersey.JerseyResourceConfiguration;
import ru.vito.web.app.jersey.model.dao.impl.AbstractRepository;
import ru.vito.web.app.jersey.model.entity.Operation;
import ru.vito.web.app.jersey.model.types.OperationType;

import javax.ws.rs.core.Application;
import java.time.LocalDateTime;
import java.util.Collections;

public abstract class BaseTest extends JerseyTest {

    protected static final String DEFAULT_ACCOUNT_ID = "1101";
    protected static final HttpStatus EXPECTED_STATUS = HttpStatus.OK_200;
    protected static final Money ONE_HUNDRED_USD = Money.of(100, "USD");

    @Override
    protected Application configure() {
        final JerseyResourceConfiguration jerseyResourceConfiguration = new JerseyResourceConfiguration();

        return jerseyResourceConfiguration;
    }

    @Before
    public void setup() {
        final Operation operation = new Operation(
                1L, DEFAULT_ACCOUNT_ID, LocalDateTime.now(), OperationType.CREDIT, ONE_HUNDRED_USD);
        AbstractRepository.data.put(DEFAULT_ACCOUNT_ID, Collections.singletonList(operation));
    }
}
