package ru.ithub;

import ru.ithub.converter.CheckConverter;
import ru.ithub.converter.impl.CheckConverterImpl;
import ru.ithub.currency.Currency;
import ru.ithub.entity.Check;
import ru.ithub.exception.ExceptionHandler;
import ru.ithub.factory.CheckFactory;
import ru.ithub.factory.impl.CheckFactoryImpl;
import ru.ithub.util.FCCAPIUtil;

import java.net.http.HttpClient;
import java.time.Duration;

public class Application {
    //TODO When we use Spring Framework it will need to be rewritten into beans
    public static final HttpClient GLOBAL_HTTP_CLIENT = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static final FCCAPIUtil FCCAPI_UTIL = new FCCAPIUtil(GLOBAL_HTTP_CLIENT);

    public static final CheckConverter CHECK_CONVERTER = new CheckConverterImpl(FCCAPI_UTIL);

    public static final CheckFactory CHECK_FACTORY = new CheckFactoryImpl();

    public static void main(String[] args) {
        ExceptionHandler.init();
    }
}
