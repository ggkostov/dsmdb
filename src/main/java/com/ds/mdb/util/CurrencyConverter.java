package com.ds.mdb.util;

import com.ds.mdb.model.WorldwideGross;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;

public class CurrencyConverter {

    public static WorldwideGross currencyToWorldwideGross(String amountString) throws ParseException {
        NumberFormat format = NumberFormat.getNumberInstance(Locale.US);
        String[] countries = {"US", "GB", "EU"};
        WorldwideGross worldwideGross = null;

        for (String countryCode : countries) {
            Currency c = Currency.getInstance(new Locale("EN", countryCode));
            if (amountString.contains(c.getSymbol())) {

                if (format instanceof DecimalFormat) {
                    ((DecimalFormat) format).setParseBigDecimal(true);
                }

                worldwideGross = new WorldwideGross(c.getCurrencyCode(), (BigDecimal) format.parse(amountString.replace(c.getSymbol(), "")));
                break;
            }
        }

        return worldwideGross;
    }

    public static String  worldwideGrossToCurrencyString(String currencyCode, BigDecimal amount) {
        return "";
    }
}
