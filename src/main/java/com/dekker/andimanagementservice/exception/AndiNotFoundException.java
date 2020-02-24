package com.dekker.andimanagementservice.exception;

import io.grpc.Status;

public class AndiNotFoundException extends AndiException {

    public AndiNotFoundException(final Status status){super(status);}

    public AndiNotFoundException(final Status status, final String message) {
        super(status, message);
    }

    public AndiNotFoundException(final Status status, final String message, final Throwable cause) {
        super(status, message, cause);
    }

    public AndiNotFoundException(Throwable cause) {
        super(cause);
    }
}