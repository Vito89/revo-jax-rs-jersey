package ru.vito.web.app.jersey;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import ru.vito.web.app.jersey.model.dao.AccountRepository;
import ru.vito.web.app.jersey.model.dao.impl.AccountRepositoryImpl;
import ru.vito.web.app.jersey.service.AccountService;
import ru.vito.web.app.jersey.service.impl.AccountServiceImpl;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class JerseyResourceConfiguration extends ResourceConfig {

    private static final String RESOURCES_PACKAGE_PREFIX = ".rest";

    public JerseyResourceConfiguration() {
        final AbstractBinder component = new AbstractBinder() {
            @Override
            protected void configure() {
                bind(AccountServiceImpl.class).to(AccountService.class).in(Singleton.class);
                bind(AccountRepositoryImpl.class).to(AccountRepository.class).in(Singleton.class);
            }
        };
        register(component);
        packages(this.getClass().getPackage().getName() + RESOURCES_PACKAGE_PREFIX);
    }
}
