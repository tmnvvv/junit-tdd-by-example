package ru.ithub.util.logging;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class AutoLoggedComponentBuilder<INTERFACE, IMPLEMENTATION extends AutoLoggedComponent> {
    private Class<INTERFACE> interfaceClass;
    private Class<IMPLEMENTATION> implementationClass;


    public AutoLoggedComponentBuilder() {
     }

    public AutoLoggedComponentBuilder setInterfaceClass(Class<INTERFACE> interfaceClass) {
        this.interfaceClass = interfaceClass;

        return this;
    }

    public AutoLoggedComponentBuilder setImplementationClass(Class<IMPLEMENTATION> implementationClass) {
        this.implementationClass = implementationClass;

        return this;
    }

    public Object build() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return Proxy.newProxyInstance(
            Thread.currentThread().getContextClassLoader(),
            new Class<?>[]{
                    interfaceClass
            },
            new InvocationHandler() {
                final IMPLEMENTATION target = implementationClass.getDeclaredConstructor().newInstance();

                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    try {
                        Object returnValue = method.invoke(target, args);

                        if (returnValue != null) {
                            target.getLogger().log(Level.INFO, methodToString(method) + " >> " + returnValue);
                        } else {
                            target.getLogger().log(Level.INFO, methodToString(method));
                        }

                        return returnValue;
                    } catch (InvocationTargetException ite) {
                        throw ite.getCause();
                    }
                }
            }
        );
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
                        .collect(Collectors.toSet())
                + ")";

        result = result.replace("[", "").replace("]", "");
        return result;
    }
}
