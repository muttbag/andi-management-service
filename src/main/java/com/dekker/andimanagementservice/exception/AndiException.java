package com.dekker.andimanagementservice.exception;

import io.grpc.Status;

public class AndiException extends RuntimeException {

    private Status status;

    public AndiException(final Status status){this.status = status;}

    public AndiException(final Status status, final String message) {
        super(message);
        this.status = status;
    }

    public AndiException(final Status status, final String message, final Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public AndiException(final Status status, Throwable cause) {
        super(cause);
        this.status = status;
    }

    public AndiException(final Throwable cause) {super(cause);
        this.status = status;}

    public Status getStatus() {
        return this.status;
    }
}
