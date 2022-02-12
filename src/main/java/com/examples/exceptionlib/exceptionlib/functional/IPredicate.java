package com.examples.exceptionlib.exceptionlib.functional;

@FunctionalInterface
public interface IPredicate<T> {
    //...
    boolean test(T t) throws Exception;
}
