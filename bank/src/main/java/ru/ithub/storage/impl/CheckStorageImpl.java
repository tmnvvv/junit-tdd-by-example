package ru.ithub.storage.impl;

import ru.fadesml.libs.AutoLoggedComponent;
import ru.ithub.entity.Check;
import ru.ithub.exception.AlreadyExistException;
import ru.ithub.exception.NotFoundException;
import ru.ithub.factory.LoggerFactory;
import ru.ithub.storage.CheckStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

public class CheckStorageImpl implements CheckStorage, AutoLoggedComponent {
    private final Logger logger = LoggerFactory.getLogger(CheckStorage.class.getName());
    private final Map<UUID, Check> storage;

    public CheckStorageImpl() {
        this.storage = new HashMap<>();
    }

    protected CheckStorageImpl(Map<UUID, Check> storage) {
        this.storage = storage;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public void create(Check check) {
        if (isExists(check)) {
            throw new AlreadyExistException(Check.class, Map.of("id", check.getId()));
        }

        storage.put(check.getId(), check);
    }

    @Override
    public void update(Check updated) {
        if (isExists(updated)) {
            storage.replace(updated.getId(), updated);

            return;
        }

        throw new NotFoundException(Check.class, Map.of("id", updated.getId()));
    }

    @Override
    public void delete(UUID id) {
        if (isExists(id)) {
            storage.remove(id);

            return;
        }

        throw new NotFoundException(Check.class, Map.of("id", id));
    }

    @Override
    public void delete(Check check) {
        if (isExists(check)) {
            storage.remove(check.getId(), check);

            return;
        }

        throw new NotFoundException(Check.class, check);
    }

    @Override
    public boolean isExists(Check check) {
        return storage.containsKey(check.getId());
    }

    @Override
    public boolean isExists(UUID id) {
        return storage.containsKey(id);
    }

    @Override
    public Check getCheck(UUID id) {
        if (isExists(id)) {
            return storage.get(id);
        }

        throw new NotFoundException(Check.class, Map.of("id", id));
    }
}
