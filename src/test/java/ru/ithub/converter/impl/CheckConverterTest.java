package ru.ithub.converter.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ithub.converter.CheckConverter;
import ru.ithub.entity.Check;
import ru.ithub.converter.impl.CheckConverterImpl;
import ru.ithub.currency.Currency;
import ru.ithub.currency.CurrencyPair;
import ru.ithub.util.FCCAPIUtil;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CheckConverterTest {
    private CheckConverter checkConverter;
    private FCCAPIUtil fccapiUtil;
    private UUID testUUID;

    @BeforeEach
    void setUp() {
        testUUID = UUID.randomUUID();
        fccapiUtil = mock(FCCAPIUtil.class);
        checkConverter = new CheckConverterImpl(fccapiUtil);
    }

    @Test
    void convertShouldUseFCCAPIUtilAndConvertAmountByRate() {
        Check check = Check.newBuilder()
                .setId(testUUID)
                .setAmount(10)
                .setCurrency(Currency.USD)
                .build();

        Check expected = Check.newBuilder()
                .setId(testUUID)
                .setAmount(700)
                .setCurrency(Currency.RUB)
                .build();

        when(fccapiUtil.getCurrencyRateOfPair(eq(CurrencyPair.of(Currency.USD, Currency.RUB)))).thenReturn(70.0);

        assertEquals(expected, checkConverter.convert(check, Currency.RUB));
    }
}