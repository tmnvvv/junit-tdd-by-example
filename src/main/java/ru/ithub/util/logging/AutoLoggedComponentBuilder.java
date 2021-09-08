package ru.ithub.util.logging;

import ru.ithub.annotation.AutoLogged;
import ru.ithub.annotation.AutoLoggingType;
import ru.ithub.annotation.Parameter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AutoLoggedComponentBuilder<INTERFACE, IMPLEMENTATION extends AutoLoggedComponent> {
    private Class<INTERFACE> interfaceClass;
    private Class<IMPLEMENTATION> implementationClass;

    private AutoLoggedComponentBuilder() {}

    public static AutoLoggedComponentBuilder.Builder newBuilder() {
        return new AutoLoggedComponentBuilder().new Builder();
    }

    public class Builder {
        private Builder() {}

        public Builder setInterfaceClass(Class<INTERFACE> interfaceClass) {
            AutoLoggedComponentBuilder.this.interfaceClass = interfaceClass;

            return this;
        }

        public Builder setImplementationClass(Class<IMPLEMENTATION> implementationClass) {
            AutoLoggedComponentBuilder.this.implementationClass = implementationClass;

            return this;
        }

        public Object build() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
            return Proxy.newProxyInstance(
                    Thread.currentThread().getContextClassLoader(),
                    new Class<?> [] {
                            AutoLoggedComponentBuilder.this.interfaceClass
                    },
                    new InvocationHandler() {
                        final IMPLEMENTATION target = AutoLoggedComponentBuilder.this.implementationClass.getDeclaredConstructor().newInstance();

                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            try {
                                Object returnValue = method.invoke(target, args);

                                if (isAutoLoggedComponent(AutoLoggedComponentBuilder.this.implementationClass)) {
                                    if (AutoLoggedComponentBuilder.this.interfaceClass.getAnnotation(AutoLoggingType.class).type() == LoggingType.MARKED) {
                                        if (getMethodFromInterface(method).getAnnotation(AutoLogged.class) != null) {
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

        private void log(Logger logger, Method method, Object returnValue) {
            if (returnValue != null) {
                logger.log(Level.INFO, methodToString(method) + " >> " + returnValue);
            } else {
                logger.log(Level.INFO, methodToString(method));
            }
        }

        private Method getMethodFromInterface(Method invoked) {
            try {
                return AutoLoggedComponentBuilder.this.interfaceClass.getMethod(invoked.getName(), invoked.getParameterTypes());
            } catch (NoSuchMethodException e) {
                return invoked;
            }
        }

        private boolean isAutoLoggedComponent(Class<IMPLEMENTATION> implementationClass) {
            boolean hasAutoLoggedComponent = false;
            boolean hasAutoLoggingTypeAnnotationInInterface = AutoLoggedComponentBuilder.this.interfaceClass.getAnnotation(AutoLoggingType.class) != null;

            for (Class<?> implemented : implementationClass.getInterfaces()) {
                if (implemented.getName().equals(AutoLoggedComponent.class.getName())) {
                    hasAutoLoggedComponent = true;
                    break;
                }
            }

            return hasAutoLoggedComponent && hasAutoLoggingTypeAnnotationInInterface;
        }

        private String methodToString(Method method) {
            String result = method.getName() + "(" +
                    Arrays.stream(method.getParameters())
                            .map(parameter -> {
                                if (parameter.getAnnotation(Parameter.class) != null) {
                                    return parameter.getAnnotation(Parameter.class).name();
                                } else {
                                    return "";
                                }
                            })
                            .collect(Collectors.toSet()) +
                    ")";

            result = result.replace("[", "").replace("]", "");
            return result;
        }
    }
}