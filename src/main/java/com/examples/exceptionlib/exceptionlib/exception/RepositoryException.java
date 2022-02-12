package com.examples.exceptionlib.exceptionlib.exception;

public class RepositoryException extends RuntimeException{
    //...

    public RepositoryException() {}

    public RepositoryException(Throwable cause)
    {
        super(cause);
    }

    public RepositoryException(String message)
    {
        super(message);
    }

    public RepositoryException(String message, Throwable cause)
    {
        super(message, cause);
    }

    @Override
    public String getMessage()
    {
        Throwable cause = this.getCause();

        //Mutable class. String alternative mutable class. Contain 'capacity' value.
        StringBuilder sb = new StringBuilder();

        return sb
                .append("Cause Exception Message: ").append(cause.getMessage())
                .append(", Exception Message: ").append(super.getMessage())
                .toString();
    }
}
