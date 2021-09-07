package ru.ithub.exception;

import ru.ithub.factory.LoggerFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class.getName());

    public static void init() {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
    }

    public void uncaughtException(Thread t, Throwable e) {
        LOGGER.log(Level.SEVERE, e.getMessage());
    }
}
