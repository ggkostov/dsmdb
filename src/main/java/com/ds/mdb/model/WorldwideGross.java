package com.ds.mdb.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(value = "Worldwide Gross object representing amount with currency code")
public class WorldwideGross {

    @ApiModelProperty(notes = "The Currency Code")
    private String currency;
    @ApiModelProperty(notes = "Worldwide Gross amount")
    private BigDecimal amount;

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
}
