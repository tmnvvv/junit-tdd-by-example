package ru.ithub.exception;

import java.util.Map;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(Class<?> clazz) {
        super(AlreadyExistException.class.getSimpleName() + ": " + clazz.getName() + " already exist! ");
    }

    public AlreadyExistException(Class<?> clazz, Map<String, Object> metadata) {
        super(AlreadyExistException.class.getSimpleName() + ": " + clazz.getName() + " already exist! " +
                metadata.toString());
    }

    public AlreadyExistException(Class<?> clazz, Object metadata) {
        super(AlreadyExistException.class.getSimpleName() + ": " + clazz.getName() + " already exist! " +
                metadata.toString());
    }
}
