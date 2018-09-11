package ru.vito.web.app.jersey.rest;

import org.glassfish.jersey.test.JerseyTest;
import ru.vito.web.app.jersey.JerseyResourceConfiguration;

import javax.ws.rs.core.Application;

public abstract class BaseTest extends JerseyTest {
    @Override
    protected Application configure() {
        final JerseyResourceConfiguration jerseyResourceConfiguration = new JerseyResourceConfiguration();

        return jerseyResourceConfiguration;
    }
}
