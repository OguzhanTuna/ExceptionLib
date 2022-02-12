package com.examples.exceptionlib.exceptionlib.functional;

@FunctionalInterface
public interface ISupplier<T> {
    T get() throws Exception;
}
