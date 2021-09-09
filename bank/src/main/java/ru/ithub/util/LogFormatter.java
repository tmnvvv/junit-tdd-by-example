package ru.ithub.util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter
{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_WHITE = "\u001B[37m";

    @Override
    public String format(LogRecord record)
    {
        StringBuilder builder = new StringBuilder();
        builder.append(getNotificationColor(record.getLevel()));

        builder.append("[Date: ");
        builder.append(calcDate(record.getMillis()));
        builder.append("]");

        builder.append(" [");
        builder.append(record.getLoggerName());
        builder.append("]");

        builder.append(" [Level: ");
        builder.append(record.getLevel().getName());
        builder.append("]");

        builder.append(ANSI_WHITE);
        builder.append(" \n ");
        builder.append("    ").append(record.getMessage());
        builder.append(" \n ");

        Object[] params = record.getParameters();

        if (params != null)
        {
            builder.append("\t");
            for (int i = 0; i < params.length; i++)
            {
                builder.append(params[i]);
                if (i < params.length - 1)
                    builder.append(", ");
            }
        }

        builder.append(ANSI_RESET);
        builder.append("\n");
        return builder.toString();
    }

    private String calcDate(long milliseconds) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(
                        new Date(milliseconds)
                );
    }

    private String getNotificationColor(Level level) {
        if (Level.INFO.equals(level)) {
            return ANSI_GREEN;
        } else if (Level.WARNING.equals(level)) {
            return ANSI_RED;
        } else if (Level.CONFIG.equals(level)) {
            return ANSI_YELLOW;
        }
        return ANSI_RESET;
    }
}