package com.examples.exceptionlib.exceptionlib.util;

import com.examples.exceptionlib.exceptionlib.exception.RepositoryException;
import com.examples.exceptionlib.exceptionlib.functional.IConsumer;
import com.examples.exceptionlib.exceptionlib.functional.IFunction;
import com.examples.exceptionlib.exceptionlib.functional.IPredicate;
import com.examples.exceptionlib.exceptionlib.functional.ISupplier;

import java.lang.reflect.Constructor;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

//Util classes is "logical static classes"
public final class ExceptionUtil {
    //...

    static <T extends RuntimeException> T doWorkForExceptionInstance(Class<? extends T> clsEx,
                                                                             String message,
                                                                             Exception cause)
    {
        T instance = null;

        try{
            Constructor paramsConstructor = clsEx.getDeclaredConstructor(String.class, Throwable.class);
            instance = (T)paramsConstructor.newInstance(message, cause); //Downcasting
        }catch (Exception ex){
            System.out.printf("Exception occurred during create exception ınstance, " +
                              "Exception Message: %s\n",
                    ex.getMessage());
        }

        return instance;
    }

    private ExceptionUtil() {}

    public static <T, R extends RuntimeException> T doWorkForException(ISupplier<? extends T> operation,
                                                                          Class<? extends R> clsEx,
                                                                          String exMessage)
    {
        T result = null;

        try{
            result = operation.get();
        }catch (Exception ex){
            throw doWorkForExceptionInstance(clsEx, exMessage, ex);
        }

        return result;
    }

    public static <T> T doWorkForException(ISupplier<? extends T> operation,
                                           Consumer<? super Exception> consumer,
                                           Class<? extends RuntimeException> clsEx,
                                           String exMessage)
    {
        T result = null;

        try{
            result = operation.get();
        }catch (Exception ex){
            consumer.accept(ex);
            throw doWorkForExceptionInstance(clsEx, exMessage, ex);
        }

        return result;
    }

    public static <T> T doWorkForException(ISupplier<? extends T> operation,
                                           Predicate<? super Exception> test,
                                           Consumer<? super Exception> consumer,
                                           Class<? extends RuntimeException> clsEx,
                                           String exMessage)
    {
        T result = null;

        try{
            result = operation.get();
        }catch (Exception ex){
            Optional.of(ex).filter(test).ifPresent(consumer);
            throw doWorkForExceptionInstance(clsEx, exMessage, ex);
        }

        return result;
    }

    public static <T> T doWorkForException(ISupplier<? extends T> operation,
                                           Runnable action,
                                           Class<? extends RuntimeException> clsEx,
                                           String exMessage)
    {
        T result = null;

        try{
            result = operation.get();
        }catch (Exception ex){
            action.run();
            throw doWorkForExceptionInstance(clsEx, exMessage, ex);
        }

        return result;
    }

    public static <T> T doWorkForException(ISupplier<? extends T> supplier,
                                           Predicate<? super Exception> test,
                                           Runnable action,
                                           Class<? extends RuntimeException> clsEx,
                                           String exMessage)
    {
        T result;

        try{
            result = supplier.get();
        }catch (Exception ex){
            Optional.of(ex).filter(test).ifPresent(exception -> action.run());
            throw doWorkForExceptionInstance(clsEx, exMessage, ex);
        }

        return result;
    }

    public static <T> T subscribe(ISupplier<? extends T> operation,
                                  Function<? super Exception, ? extends T> map)
    {
        try {
            return operation.get();
        }catch (Exception ex){
            return map.apply(ex);
        }
    }

    public static <T> T subscribe(ISupplier<? extends T> operation,
                                  Consumer<? super Exception> consumer,
                                  Function<? super Exception, ? extends T> map)
    {
        try { //Try-catch deyimi
            return operation.get();
        }catch (Exception ex){
            consumer.accept(ex);
            return map.apply(ex);
        }
    }

    public static <T> T subscribe(ISupplier<? extends T> operation,
                                  Runnable action,
                                  Function<? super Exception, ? extends T> function)
    {
        try{
            return operation.get();
        }catch (Exception ex){
            action.run();
            return function.apply(ex);
        }
    }
}