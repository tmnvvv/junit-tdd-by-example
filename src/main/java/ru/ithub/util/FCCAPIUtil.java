package ru.ithub.util;

import ru.ithub.annotation.ApiModule;
import ru.ithub.currency.CurrencyPair;
import ru.ithub.exception.ApiRequestException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Free Currency Converter API
 * https://free.currencyconverterapi.com
 */

@ApiModule(name = "Free Currency Converter API", link = "https://free.currencyconverterapi.com")
public class FCCAPIUtil {
    private final HttpClient httpClient;

    private static final String apiKey = "bb049989838c5352c230";

    public FCCAPIUtil(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    String convertCodeOf(CurrencyPair pair) {
        return pair.getFrom().name() + "_" + pair.getTo().name();
    }

    Double getCurrencyRateFromJsonString(String json) {
        return Double.parseDouble(json.substring(18).replace("}}", ""));
    }

    public Double getCurrencyRateOfPair(CurrencyPair pair) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://free.currconv.com/api/v7/convert?q=" + convertCodeOf(pair) + "&compact=y&apiKey="+apiKey))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return getCurrencyRateFromJsonString(response.body());
            } else {
                throw new ApiRequestException(FCCAPIUtil.class);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        throw new ApiRequestException(FCCAPIUtil.class);
    }
}
