package com.ds.mdb.util;

import com.ds.mdb.model.WorldwideGross;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

class CurrencyConverterTest {

    @Test
    void currencyToWorldwideGross() throws ParseException {
        WorldwideGross worldwideGross = new WorldwideGross("EUR", new BigDecimal("0.50"));
        WorldwideGross converted = CurrencyConverter.currencyToWorldwideGross("0.50EUR");

        assertEquals("Equal amount ", worldwideGross.getAmount(), converted.getAmount());
        assertEquals("Equal currency ", worldwideGross.getCurrency(), converted.getCurrency());

        worldwideGross = new WorldwideGross("GBP", new BigDecimal("3.50"));
        converted = CurrencyConverter.currencyToWorldwideGross("3.50GBP");

        assertEquals("Equal amount ", worldwideGross.getAmount(), converted.getAmount());
        assertEquals("Equal currency ", worldwideGross.getCurrency(), converted.getCurrency());

        worldwideGross = new WorldwideGross("USD", new BigDecimal("3.50"));
        converted = CurrencyConverter.currencyToWorldwideGross("$3.50");

        assertEquals("Equal amount ", worldwideGross.getAmount(), converted.getAmount());
        assertEquals("Equal currency ", worldwideGross.getCurrency(), converted.getCurrency());

        worldwideGross = new WorldwideGross("USD", new BigDecimal("3.50"));
        converted = CurrencyConverter.currencyToWorldwideGross("3.51EUR");

        assertNotEquals(worldwideGross.getAmount(), converted.getAmount());
        assertNotEquals(worldwideGross.getCurrency(), converted.getCurrency());
    }
}