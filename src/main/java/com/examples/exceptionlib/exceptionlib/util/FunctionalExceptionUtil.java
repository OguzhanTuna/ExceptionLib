package com.examples.exceptionlib.exceptionlib.util;

import com.examples.exceptionlib.exceptionlib.exception.RepositoryException;
import com.examples.exceptionlib.exceptionlib.exception.ServiceException;
import com.examples.exceptionlib.exceptionlib.functional.IRunnable;
import com.examples.exceptionlib.exceptionlib.functional.ISupplier;

public final class FunctionalExceptionUtil {
    //...

    private FunctionalExceptionUtil() {}

    public static <T> T doWorkForRepositoryException(ISupplier<? extends T> operation,
                                                     String exMessage)
    {
        T result = null;

        try{
            result = operation.get();
        }catch (Exception ex){
            throw ExceptionUtil.doWorkForExceptionInstance(RepositoryException.class,exMessage, ex);
        }

        return result;
    }

    public static void doWorkForRepositoryException(IRunnable operation,
                                                    String exMessage)
    {
        try {
            operation.run();
        }catch (Exception ex){
            throw ExceptionUtil.doWorkForExceptionInstance(RepositoryException.class, exMessage, ex);
        }
    }

    public static <T> T doWorkForServiceException(ISupplier<? extends T> operation,
                                                  String exMessage)
    {
        T result = null;

        try{
            result = operation.get();
        }catch (Exception ex){
            throw ExceptionUtil.doWorkForExceptionInstance(ServiceException.class, exMessage, ex);
        }

        return result;
    }

    public static void doWorkForServiceException(IRunnable operation,
                                                 String exMessage)
    {
        try {
            operation.run();
        }catch (Exception ex){
            throw ExceptionUtil.doWorkForExceptionInstance(ServiceException.class, exMessage, ex);
        }
    }
}
