package ru.ithub;

import ru.fadesml.libs.AutoLoggedComponentBuilder;
import ru.ithub.converter.CheckConverter;
import ru.ithub.converter.impl.CheckConverterImpl;
import ru.ithub.currency.Currency;
import ru.ithub.entity.Check;
import ru.ithub.factory.CheckFactory;
import ru.ithub.factory.LoggerFactory;
import ru.ithub.factory.impl.CheckFactoryImpl;
import ru.ithub.handler.ExceptionHandler;
import ru.ithub.storage.CheckStorage;
import ru.ithub.storage.impl.CheckStorageImpl;
import ru.ithub.util.FCCAPIUtil;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("ALL")
public class Application {
    //TODO When we use Spring Framework it will need to be rewritten into beans
    public static final HttpClient GLOBAL_HTTP_CLIENT = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static final Logger GLOBAL_LOGGER = Logger.getGlobal();

    public static final FCCAPIUtil FCCAPI_UTIL = new FCCAPIUtil(GLOBAL_HTTP_CLIENT);

    public static final CheckConverter CHECK_CONVERTER = new CheckConverterImpl(FCCAPI_UTIL);

    public static CheckFactory CHECK_FACTORY = AutoLoggedComponentBuilder.create(CheckFactory.class, CheckFactoryImpl.class);
    public static CheckStorage CHECK_STORAGE = AutoLoggedComponentBuilder.create(CheckStorage.class, CheckStorageImpl.class);

    public static void main(String[] args) {
        //initialization
        LoggerFactory.init();
        ExceptionHandler.init();

        Check check = CHECK_FACTORY.euro(100);

        CHECK_FACTORY.dollar(200);

        CHECK_FACTORY.rubble(300);

        CHECK_STORAGE.create(check);

        CHECK_STORAGE.getCheck(check.getId());

        check = CHECK_CONVERTER.convert(check, Currency.USD);

        CHECK_STORAGE.update(check);

        GLOBAL_LOGGER.log(Level.INFO, check.toString());

        //throws NotFoundException from ExceptionHandler with Level: WARNING
        Check checkFromStorage = CHECK_STORAGE.getCheck(UUID.randomUUID());
    }
}
