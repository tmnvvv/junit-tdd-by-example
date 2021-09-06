package ru.ithub.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ithub.entity.Check;
import ru.ithub.converter.impl.CheckConverterImpl;
import ru.ithub.currency.Currency;
import ru.ithub.currency.CurrencyPair;
import ru.ithub.util.FCCAPIUtil;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CheckConverterTest {
    private CheckConverter checkConverter;
    private FCCAPIUtil fccapiUtil;

    @BeforeEach
    void setUp() {
        fccapiUtil = mock(FCCAPIUtil.class);
        checkConverter = new CheckConverterImpl(fccapiUtil);
    }

    @Test
    void convertShouldUseFCCAPIUtilAndConvertAmountByRate() {
        Check check = new Check(10, Currency.USD);
        Check expected = new Check(700, Currency.RUB);

        when(fccapiUtil.getCurrencyRateOfPair(eq(CurrencyPair.of(Currency.USD, Currency.RUB)))).thenReturn(70.0);

        assertEquals(expected, checkConverter.convert(check, Currency.RUB));
    }
}