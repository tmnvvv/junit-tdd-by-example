package ru.ithub.exception;

import ru.ithub.annotation.ApiModule;

public class ApiRequestException extends RuntimeException {
    public ApiRequestException(Class<?> clazz) {
        super("Error: Calling api module { name=" + clazz.getAnnotation(ApiModule.class).name() + ", \n link=" + clazz.getAnnotation(ApiModule.class).link() + " \n }");
    }
}
