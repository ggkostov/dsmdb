package com.ds.mdb.model;

import com.ds.mdb.util.CurrencyConverter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.text.ParseException;

@ApiModel(value = "Worldwide Gross object representing price with currency code")
public class WorldwideGross {

    @ApiModelProperty(notes = "The Currency Code")
    private String currency;
    @ApiModelProperty(notes = "Worldwide Gross amount")
    private BigDecimal amount;

    public WorldwideGross(String amount) {
        try {
            WorldwideGross worldwideGross = CurrencyConverter.currencyToWorldwideGross(amount);

            this.currency = worldwideGross.getCurrency();
            this.amount = worldwideGross.getAmount();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public WorldwideGross(String currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return CurrencyConverter.worldwideGrossToCurrencyString(currency, amount);
    }
}
