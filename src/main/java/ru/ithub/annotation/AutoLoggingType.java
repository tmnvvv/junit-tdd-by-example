package ru.ithub.annotation;

import ru.ithub.util.logging.LoggingType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AutoLoggingType {
    LoggingType type() default LoggingType.ALL;
}
