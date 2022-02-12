package com.examples.exceptionlib.exceptionlib.exception;

public class ServiceException extends RuntimeException{
    //...

    //Default constrcutor
    public ServiceException() {}

    public ServiceException(String message)
    {
        super(message);
    }

    public ServiceException(Throwable cause)
    {
        super(cause);
    }

    public ServiceException(String message, Throwable cause)
    {
        super(message, cause);
    }

    @Override
    public String getMessage()
    {
        Throwable cause = this.getCause();

        StringBuilder sb = new StringBuilder();

        return sb
                .append("Cause Exception Message: ").append(cause.getMessage())
                .append(", Exception Message: ").append(super.getMessage())
                .toString();
    }
}
