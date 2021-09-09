package ru.ithub.storage;

import ru.ithub.entity.Check;

import java.util.UUID;

public interface CheckStorage {
    void create(Check check);

    void update(Check updated);

    void delete(UUID id);

    void delete(Check check);

    boolean isExists(Check check);

    boolean isExists(UUID id);

    Check getCheck(UUID id);
}
