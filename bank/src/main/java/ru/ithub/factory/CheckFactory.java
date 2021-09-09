package ru.ithub.factory;

import ru.fadesml.libs.LoggingType;
import ru.fadesml.libs.annotation.AutoLoggingType;
import ru.fadesml.libs.annotation.MethodInfo;
import ru.ithub.entity.Check;

@AutoLoggingType(type = LoggingType.ALL)
public interface CheckFactory {

    @MethodInfo(name = "dollar", parameters = {"Amount of money", "Test Param"}, description = "Method create Check from USD")
    Check dollar(double amount);

    Check euro(double amount);

    Check rubble(double amount);

    Check franc(double amount);
}
