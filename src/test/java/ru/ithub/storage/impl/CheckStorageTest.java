package ru.ithub.storage.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.ithub.entity.Check;
import ru.ithub.exception.AlreadyExistException;
import ru.ithub.exception.NotFoundException;
import ru.ithub.factory.CheckFactory;
import ru.ithub.factory.impl.CheckFactoryImpl;
import ru.ithub.storage.CheckStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


class CheckStorageTest {
    private Map<UUID, Check> checkMap;

    private CheckStorage checkStorage;
    private CheckFactory checkFactory;

    @BeforeEach
    void setUp() {
        checkMap = mock(Map.class);
        checkFactory = new CheckFactoryImpl();
        checkStorage = new CheckStorageImpl(checkMap);
    }

    @Test
    void createShouldPutCheckInStorageMap() {
        Check check = checkFactory.dollar(100);

        checkStorage.create(check);

        verify(checkMap).containsKey(eq(check.getId()));
        verify(checkMap).put(eq(check.getId()), eq(check));
    }

    @Test
    void createShouldThrowAlreadyExistException() {
        Check check = checkFactory.dollar(100);
        when(checkMap.containsKey(eq(check.getId()))).thenReturn(true);

        assertThrows(AlreadyExistException.class, () -> checkStorage.create(check));
    }

    @Test
    void updateShouldReplaceCheckInStorage() {
        Check update = checkFactory.dollar(100);

        when(checkMap.containsKey(eq(update.getId()))).thenReturn(true);

        checkStorage.update(update);

        verify(checkMap).containsKey(eq(update.getId()));
        verify(checkMap).replace(eq(update.getId()), eq(update));
    }

    @Test
    void updateShouldThrowNotFoundException() {
        Check update = checkFactory.dollar(100);

        when(checkMap.containsKey(eq(update.getId()))).thenReturn(false);

        assertThrows(NotFoundException.class, () -> checkStorage.update(update));

        verify(checkMap).containsKey(eq(update.getId()));
    }

    @Test
    void deleteByIdShouldDeleteCheckFromStorage() {
        UUID existing = UUID.randomUUID();

        when(checkMap.containsKey(eq(existing))).thenReturn(true);

        checkStorage.delete(existing);

        verify(checkMap).containsKey(eq(existing));
        verify(checkMap).remove(eq(existing));
    }

    @Test
    void deleteByIdShouldThrowNotFoundException() {
        UUID existing = UUID.randomUUID();

        when(checkMap.containsKey(eq(existing))).thenReturn(false);

        assertThrows(NotFoundException.class, () -> checkStorage.delete(existing));

        verify(checkMap).containsKey(eq(existing));
    }

    @Test
    void deleteByCheckShouldDeleteCheckFromStorage() {
        Check existing = checkFactory.dollar(100);

        when(checkMap.containsKey(eq(existing.getId()))).thenReturn(true);

        checkStorage.delete(existing);

        verify(checkMap).containsKey(eq(existing.getId()));
        verify(checkMap).remove(eq(existing.getId()), eq(existing));
    }

    @Test
    void deleteByCheckShouldThrowNotFoundException() {
        Check existing = checkFactory.dollar(100);

        when(checkMap.containsKey(eq(existing.getId()))).thenReturn(false);

        assertThrows(NotFoundException.class, () -> checkStorage.delete(existing));

        verify(checkMap).containsKey(eq(existing.getId()));
    }

    @Test
    void isExistsByIdShouldCheckStorageOnContainsKey() {
        UUID existing = UUID.randomUUID();

        checkStorage.isExists(existing);

        verify(checkMap).containsKey(eq(existing));
    }

    @Test
    void isExistsByCheckShouldCheckStorageOnContainsKey() {
        Check existing = checkFactory.dollar(100);

        checkStorage.isExists(existing);

        verify(checkMap).containsKey(eq(existing.getId()));
    }

    @Test
    void getCheckShouldShouldGetCheckFromStorage() {
        Check existing = checkFactory.dollar(100);

        when(checkMap.containsKey(eq(existing.getId()))).thenReturn(true);
        when(checkMap.get(eq(existing.getId()))).thenReturn(existing);

        assertEquals(existing, checkStorage.getCheck(existing.getId()));

        verify(checkMap).containsKey(eq(existing.getId()));
        verify(checkMap).get(eq(existing.getId()));
    }

    @Test
    void getCheckShouldShouldThrowNotFoundException() {
        Check existing = checkFactory.dollar(100);

        when(checkMap.containsKey(eq(existing.getId()))).thenReturn(false);

        assertThrows(NotFoundException.class, () -> checkStorage.getCheck(existing.getId()));

        verify(checkMap).containsKey(eq(existing.getId()));
    }
}