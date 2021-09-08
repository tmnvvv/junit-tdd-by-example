package ru.ithub;

import ru.ithub.converter.CheckConverter;
import ru.ithub.converter.impl.CheckConverterImpl;
import ru.ithub.entity.Check;
import ru.ithub.handler.ExceptionHandler;
import ru.ithub.factory.CheckFactory;
import ru.ithub.factory.LoggerFactory;
import ru.ithub.factory.impl.CheckFactoryImpl;
import ru.ithub.storage.CheckStorage;
import ru.ithub.storage.impl.CheckStorageImpl;
import ru.ithub.util.FCCAPIUtil;
import ru.ithub.util.logging.AutoLoggedComponentBuilder;

import java.lang.reflect.InvocationTargetException;
import java.net.http.HttpClient;
import java.time.Duration;
import java.util.UUID;

public class Application {
    //TODO When we use Spring Framework it will need to be rewritten into beans
    public static final HttpClient GLOBAL_HTTP_CLIENT = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static final FCCAPIUtil FCCAPI_UTIL = new FCCAPIUtil(GLOBAL_HTTP_CLIENT);

    public static final CheckConverter CHECK_CONVERTER = new CheckConverterImpl(FCCAPI_UTIL);

    public static CheckFactory CHECK_FACTORY = null;
    public static CheckStorage CHECK_STORAGE = null;

    public static void main(String[] args) {
        //initialization
        LoggerFactory.init();
        ExceptionHandler.init();
        initAutoLoggedComponents();

        Check check = CHECK_FACTORY.euro(100);

        CHECK_FACTORY.dollar(200);

        CHECK_FACTORY.rubble(300);

        CHECK_STORAGE.create(check);

        //should throw Not Found Exception
        Check checkFromStorage = CHECK_STORAGE.getCheck(UUID.randomUUID());
    }

    /*
     * TODO
     *  Unchecked call to 'setImplementationClass(Class<IMPLEMENTATION>)' as a member of raw type 'ru.ithub.util.loggining.AutoLoggedComponentBuilder'
     *  Unchecked call to 'setInterfaceClass(Class<INTERFACE>)' as a member of raw type 'ru.ithub.util.loggining.AutoLoggedComponentBuilder'
     */
    public static void initAutoLoggedComponents() {
        try {
            CHECK_FACTORY = (CheckFactory) new AutoLoggedComponentBuilder()
                .setInterfaceClass(CheckFactory.class)
                .setImplementationClass(CheckFactoryImpl.class)
                .build();

            CHECK_STORAGE = (CheckStorage) new AutoLoggedComponentBuilder()
                    .setInterfaceClass(CheckStorage.class)
                    .setImplementationClass(CheckStorageImpl.class)
                    .build();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
