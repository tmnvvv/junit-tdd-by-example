package ru.ithub.converter.impl;

import ru.ithub.entity.Check;
import ru.ithub.converter.CheckConverter;
import ru.ithub.currency.Currency;
import ru.ithub.currency.CurrencyPair;
import ru.ithub.util.FCCAPIUtil;

public class CheckConverterImpl implements CheckConverter {
    private final FCCAPIUtil fccapiUtil;

    public CheckConverterImpl(FCCAPIUtil fccapiUtil) {
        this.fccapiUtil = fccapiUtil;
    }

    @Override
    public Check convert(Check check, Currency currency) {
        if (check.getCurrency() == currency) return check;
        return new Check(check.getAmount() * fccapiUtil.getCurrencyRateOfPair(CurrencyPair.of(check.getCurrency(), currency)), currency);
    }
}
