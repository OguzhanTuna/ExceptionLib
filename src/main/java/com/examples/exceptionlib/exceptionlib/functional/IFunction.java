package com.examples.exceptionlib.exceptionlib.functional;


@FunctionalInterface
public interface IFunction<T, R> {
    //...
    R apply(T t) throws Exception;
}
