package ru.ithub.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ithub.annotation.ApiModule;
import ru.ithub.currency.Currency;
import ru.ithub.currency.CurrencyPair;
import ru.ithub.exception.ApiRequestException;

import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;
import java.util.function.BiPredicate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FCCAPIUtilTest {
    private FCCAPIUtil fccapiUtil;

    @BeforeEach
    void setUp() {
        fccapiUtil = new FCCAPIUtil(
                HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .connectTimeout(Duration.ofSeconds(10))
                    .build()
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
}