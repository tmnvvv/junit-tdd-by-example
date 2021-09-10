package ru.fadesml.libs;

import ru.fadesml.libs.annotation.AutoLogged;
import ru.fadesml.libs.annotation.AutoLoggingType;
import ru.fadesml.libs.annotation.MethodInfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoLoggedComponentBuilder {
    @SuppressWarnings("unchecked")
    public static <INTERFACE, IMPLEMENTATION extends AutoLoggedComponent> INTERFACE create(Class<INTERFACE> interfaceClass, Class<IMPLEMENTATION> implementationClass) {
        return (INTERFACE) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class<?> [] {
                        interfaceClass
                },
                new InvocationHandler() {
                    IMPLEMENTATION target;

                    {
                        try {
                            target = implementationClass.getDeclaredConstructor().newInstance();
                        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                            e.printStackTrace();
                            target = null;
                        }
                    }

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        try {
                            Object returnValue = method.invoke(target, args);

                            if (isAutoLoggedComponent(interfaceClass, implementationClass)) {
                                if (interfaceClass.getAnnotation(AutoLoggingType.class).type() == LoggingType.MARKED) {
                                    if (getMethodFromInterface(method, interfaceClass).getAnnotation(AutoLogged.class) != null) {
                                        log(target.getLogger(), method, returnValue);
                                    }
                                } else {
                                    log(target.getLogger(), method, returnValue);
                                }
                            }

                            return returnValue;
                        } catch (InvocationTargetException ite) {
                            throw ite.getCause();
                        }
                    }
                }
        );
    }

    private static void log(Logger logger, Method method, Object returnValue) {
        if (returnValue != null) {
            logger.log(Level.INFO, methodToString(method) + " >> " + returnValue);
        } else {
            logger.log(Level.INFO, methodToString(method));
        }
    }

    private static <INTERFACE> Method getMethodFromInterface(Method invoked, Class<INTERFACE> interfaceClass) {
        try {
            return interfaceClass.getMethod(invoked.getName(), invoked.getParameterTypes());
        } catch (NoSuchMethodException e) {
            return invoked;
        }
    }

    private static <INTERFACE, IMPLEMENTATION> boolean isAutoLoggedComponent(Class<INTERFACE> interfaceClass, Class<IMPLEMENTATION> implementationClass) {
        boolean hasAutoLoggedComponent = false;
        boolean hasAutoLoggingTypeAnnotationInInterface = interfaceClass.getAnnotation(AutoLoggingType.class) != null;

        for (Class<?> implemented : implementationClass.getInterfaces()) {
            if (implemented.getName().equals(AutoLoggedComponent.class.getName())) {
                hasAutoLoggedComponent = true;
                break;
            }
        }

        return hasAutoLoggedComponent && hasAutoLoggingTypeAnnotationInInterface;
    }

    private static String methodToString(Method method) {
        String result = "";

        if (method.getAnnotation(MethodInfo.class) != null) {
            MethodInfo methodInfo = method.getAnnotation(MethodInfo.class);

            result = result.concat(methodInfo.name() + "(");
            String parameters = "";

            for (int i = 0; i < methodInfo.parameters().length; i++) {
                parameters = parameters.concat(methodInfo.parameters()[i] + ", ");
            }

            if(parameters.endsWith(", ")) { parameters = parameters.substring(0, parameters.length() - 2); }

            result =  result.concat(parameters + "), description("+ methodInfo.description()+")");

        } else {
            result =  result.concat(method.getName() + "(");
            String parameters = "";

            for (int i = 0; i < method.getParameterTypes().length; i++) {
                parameters = parameters.concat(method.getParameterTypes()[i].getSimpleName() + ", ");
            }

            if(parameters.endsWith(", ")) { parameters = parameters.substring(0, parameters.length() - 2); }

            result = result.concat(parameters + ")");
        }

        return result;
    }
}