package ru.ithub.factory;

import ru.ithub.annotation.AutoLogged;
import ru.ithub.annotation.AutoLoggingType;
import ru.ithub.entity.Check;
import ru.ithub.annotation.Parameter;
import ru.ithub.util.logging.LoggingType;

@AutoLoggingType(type = LoggingType.MARKED)
public interface CheckFactory {
    @AutoLogged Check dollar(@Parameter(name = "amount of money") double amount);

    @AutoLogged Check euro(@Parameter(name = "amount of money") double amount);

    Check rubble(@Parameter(name = "amount of money") double amount);

    Check franc(@Parameter(name = "amount of money") double amount);
}
