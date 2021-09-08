package ru.ithub.storage.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ithub.entity.Check;
import ru.ithub.exception.AlreadyExistException;
import ru.ithub.factory.CheckFactory;
import ru.ithub.factory.impl.CheckFactoryImpl;
import ru.ithub.storage.CheckStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CheckStorageTest {

    private Map<UUID, Check> checkMap;
    private CheckStorage checkStorage;
    private CheckFactory checkFactory;

    @BeforeEach
    void setUp() {
        checkMap = new HashMap<>();
        checkFactory = new CheckFactoryImpl();
        checkStorage = new CheckStorageImpl(checkMap);
    }

    @Test
    void createShouldPutCheckInStorageMap() {
        Check check = checkFactory.dollar(100);
        checkStorage.create(check);

        assertEquals(checkMap.get(check.getId()), check);
        checkMap.clear();
    }

    @Test
    void createShouldThrowAlreadyExistException() {
        Check check = checkFactory.dollar(100);
        checkStorage.create(check);

        assertThrows(AlreadyExistException.class, () -> checkStorage.create(check));
        checkMap.clear();
    }

    //NotFoundException
    @Test
    void update() {
    }

    //NotFoundException
    @Test
    void deleteById() {
    }

    //NotFoundException
    @Test
    void deleteByCheck() {
    }

    @Test
    void isExists() {
    }

    @Test
    void testIsExists() {
    }

    //NotFoundException
    @Test
    void getCheck() {
    }
}