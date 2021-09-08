package ru.ithub.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ithub.currency.Currency;
import ru.ithub.currency.CurrencyPair;
import ru.ithub.exception.ApiRequestException;

import java.io.IOException;
import java.net.http.HttpClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FCCAPIUtilTest {
    private FCCAPIUtil fccapiUtil;
    private HttpClient httpClient;

    @BeforeEach
    void setUp() {
        this.httpClient = mock(HttpClient.class);
        fccapiUtil = new FCCAPIUtil(
               httpClient
        );
    }

    @Test
    void convertCodeOf() {
        String expected = "RUB_USD";
        assertEquals(expected, fccapiUtil.convertCodeOf(CurrencyPair.of(Currency.RUB, Currency.USD)));
    }

    @Test
    void getCurrencyRateFromJsonString() {
        Double expected = 70.1234;
        assertEquals(expected, fccapiUtil.getCurrencyRateFromJsonString("{\"USD_RUB\":{\"val\":70.1234}}"));
    }

    @Test
    void getCurrencyRateFromJsonStringShouldThrowApiRequestException() {
        ErrorHttpResponse response = new ErrorHttpResponse();
        try {
            when(httpClient.send(any(), any())).thenReturn(response);
        } catch (IOException | InterruptedException ignored) { }

        String expectedMessage = "Error: Calling api module { name='Free Currency Converter API', link='https://free.currencyconverterapi.com'}";
        ApiRequestException actualException = assertThrows(ApiRequestException.class, () -> fccapiUtil.getCurrencyRateOfPair(CurrencyPair.of(Currency.RUB, Currency.USD)));
        assertEquals(expectedMessage, actualException.getMessage());
    }

}