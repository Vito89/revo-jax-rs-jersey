package ru.vito.web.app.jersey.rest.commons;

import org.glassfish.grizzly.http.util.HttpStatus;
import org.javamoney.moneta.Money;

public class TestUtils {

    public static final String FIRST_ACCOUNT_ID = "1101";
    public static final String SECOND_ACCOUNT_ID = "1102";
    public static final HttpStatus EXPECTED_STATUS = HttpStatus.OK_200;
    public static final Money ONE_HUNDRED_USD = Money.of(100, "USD");
    public static final Money TWENTY_USD = Money.of(20, "USD");

}
