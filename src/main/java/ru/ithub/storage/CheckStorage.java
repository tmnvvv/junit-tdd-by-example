package ru.ithub.storage;

import ru.ithub.annotation.AutoLoggingType;
import ru.ithub.entity.Check;
import ru.ithub.annotation.Parameter;
import ru.ithub.util.logging.LoggingType;

import java.util.UUID;

@AutoLoggingType(type = LoggingType.ALL)
public interface CheckStorage {
    void create(@Parameter(name = "creation check") Check check);

    void update(@Parameter(name = "updated check") Check updated);

    void delete(@Parameter(name = "existing check uuid") UUID id);

    void delete(@Parameter(name = "existing check") Check check);

    boolean isExists(@Parameter(name = "existing check") Check check);

    boolean isExists(@Parameter(name = "existing check uuid") UUID id);

    Check getCheck(@Parameter(name = "existing check uuid") UUID id);
}
