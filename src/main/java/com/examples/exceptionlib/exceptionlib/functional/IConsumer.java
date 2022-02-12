package com.examples.exceptionlib.exceptionlib.functional;

@FunctionalInterface
public interface IConsumer<T> {
    //...
    void accept(T t) throws Exception;
}
