package ru.ithub.factory;

import ru.ithub.util.logging.LogFormatter;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class LoggerFactory {
    public static void init() {
        Logger globalLogger = Logger.getGlobal();
        globalLogger.setUseParentHandlers(false);
        globalLogger.addHandler(getConsoleHandler());
    }

    private static ConsoleHandler getConsoleHandler() {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(logFormatter);

        return consoleHandler;
    }

    private static final LogFormatter logFormatter = new LogFormatter();

    public static LogFormatter getLogFormatter() {
        return logFormatter;
    }

    public static Logger getLogger(String caller) {
        Logger logger = Logger.getLogger(caller);
        logger.setUseParentHandlers(false);
        logger.addHandler(getConsoleHandler());
        return logger;
    }
}
