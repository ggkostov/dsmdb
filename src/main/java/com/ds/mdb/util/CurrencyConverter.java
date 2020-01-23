package com.ds.mdb.util;

import com.ds.mdb.model.WorldwideGross;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

public class CurrencyConverter {

    public static WorldwideGross currencyToWorldwideGross(String amountString) throws ParseException {
        NumberFormat format = NumberFormat.getNumberInstance(Locale.US);
        String[] countries = {"US", "GB", "DE"};

        for (String countryCode : countries) {
            Currency c = Currency.getInstance(new Locale("", countryCode));
            String symbol = c.getSymbol(Locale.US);
            if (amountString.contains(symbol)) {

                if (format instanceof DecimalFormat) {
                    ((DecimalFormat) format).setParseBigDecimal(true);
                }

                return new WorldwideGross(c.getCurrencyCode(), (BigDecimal) format.parse(amountString.replace(symbol, "")));
            }
        }

        return new WorldwideGross("USD", new BigDecimal(0));
    }
}
