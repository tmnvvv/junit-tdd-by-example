package ru.ithub.storage;

import ru.ithub.entity.Check;
import ru.ithub.annotation.Parameter;

import java.util.UUID;

public interface CheckStorage {
    void create(@Parameter(name = "creation check") Check check);

    void update(@Parameter(name = "updated check") Check updated);

    void delete(@Parameter(name = "existing check uuid") UUID id);

    void delete(@Parameter(name = "existing check") Check check);

    boolean isExists(@Parameter(name = "existing check") Check check);

    boolean isExists(@Parameter(name = "existing check uuid") UUID id);

    Check getCheck(@Parameter(name = "existing check uuid") UUID id);
}
