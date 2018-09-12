package ru.vito.web.app.jersey.rest.service;

import org.javamoney.moneta.FastMoney;
import org.javamoney.moneta.Money;
import org.junit.Ignore;
import org.junit.Test;
import ru.vito.web.app.jersey.rest.BaseTest;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import java.util.Locale;

import static org.junit.Assert.*;

public class MoneyTest extends BaseTest {

    @Test
    public void givenCurrencies_whenCompared_thanNotequal() {
        MonetaryAmount oneDolar = Monetary.getDefaultAmountFactory()
                .setCurrency("USD").setNumber(1).create();
        Money oneEuro = Money.of(1, "EUR");

        assertNotEquals(oneEuro, FastMoney.of(1, "EUR"));
        assertEquals(oneDolar, Money.of(1, "USD"));
    }

    @Test
    public void givenAmounts_whenSummed_thanCorrect() {
        MonetaryAmount[] monetaryAmounts = new MonetaryAmount[]{
                Money.of(100, "CHF"),
                Money.of(10.20, "CHF"),
                Money.of(1.15, "CHF")
        };
        Money sumAmtCHF = Money.of(0, "CHF");
        for (MonetaryAmount monetaryAmount : monetaryAmounts) {
            sumAmtCHF = sumAmtCHF.add(monetaryAmount);
        }
        assertEquals("CHF 111.35", sumAmtCHF.toString());
    }

    @Test
    @Ignore
    public void givenLocale_whenFormatted_thanEquals() {
        MonetaryAmount oneDollar = Monetary.getDefaultAmountFactory()
                .setCurrency("USD").setNumber(1).create();

        MonetaryAmountFormat formatUSD = MonetaryFormats.getAmountFormat(Locale.US);
        String usFormatted = formatUSD.format(oneDollar);

        assertEquals("USD 1", oneDollar.toString());
        assertNotNull(formatUSD);
        assertEquals("USD1.00", usFormatted);
    }
}
