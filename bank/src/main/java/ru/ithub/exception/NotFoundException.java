package ru.ithub.exception;

import java.util.Map;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Class<?> clazz) {
        super(NotFoundException.class.getSimpleName() + ": " + clazz.getName() + " not found! ");
    }

    public NotFoundException(Class<?> clazz, Map<String, Object> metadata) {
        super(NotFoundException.class.getSimpleName() + ": " + clazz.getName() + " not found! " +
                metadata.toString());
    }

    public NotFoundException(Class<?> clazz, Object metadata) {
        super(NotFoundException.class.getSimpleName() + ": " + clazz.getName() + " not found! " +
                metadata.toString());
    }
}
