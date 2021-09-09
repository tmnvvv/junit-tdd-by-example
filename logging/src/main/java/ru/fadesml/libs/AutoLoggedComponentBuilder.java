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

public class AutoLoggedComponentBuilder<INTERFACE, IMPLEMENTATION extends AutoLoggedComponent> {
    private Class<INTERFACE> interfaceClass;
    private Class<IMPLEMENTATION> implementationClass;

    private AutoLoggedComponentBuilder() {}

    public static <INTERFACE, IMPLEMENTATION extends AutoLoggedComponent> AutoLoggedComponentBuilder.Builder newBuilder() {
        return new AutoLoggedComponentBuilder<INTERFACE, IMPLEMENTATION>().new Builder();
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
            return  Proxy.newProxyInstance(
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
}