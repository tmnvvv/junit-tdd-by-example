package ru.ithub;

import ru.fadesml.libs.AutoLoggedComponentBuilder;
import ru.ithub.entity.Check;
import ru.ithub.factory.CheckFactory;
import ru.ithub.factory.LoggerFactory;
import ru.ithub.factory.impl.CheckFactoryImpl;
import ru.ithub.handler.ExceptionHandler;
import ru.ithub.storage.CheckStorage;
import ru.ithub.storage.impl.CheckStorageImpl;
import ru.ithub.util.FCCAPIUtil;

import java.lang.reflect.InvocationTargetException;
import java.net.http.HttpClient;
import java.time.Duration;
import java.util.UUID;

@SuppressWarnings("ALL")
public class Application {
    //TODO When we use Spring Framework it will need to be rewritten into beans
    public static final HttpClient GLOBAL_HTTP_CLIENT = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static final FCCAPIUtil FCCAPI_UTIL = new FCCAPIUtil(GLOBAL_HTTP_CLIENT);

    // --Commented out by Inspection (08.09.2021 20:39):public static final CheckConverter CHECK_CONVERTER = new CheckConverterImpl(FCCAPI_UTIL);

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

        //should throw NotFoundException
        Check checkFromStorage = CHECK_STORAGE.getCheck(UUID.randomUUID());
    }

    public static void initAutoLoggedComponents() {
        try {
            CHECK_FACTORY = (CheckFactory) AutoLoggedComponentBuilder.newBuilder()
                    .setInterfaceClass(CheckFactory.class)
                    .setImplementationClass(CheckFactoryImpl.class)
                    .build();

            CHECK_STORAGE = (CheckStorage) AutoLoggedComponentBuilder.newBuilder()
                    .setInterfaceClass(CheckStorage.class)
                    .setImplementationClass(CheckStorageImpl.class)
            .build();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
