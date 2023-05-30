package sopt.org.fouthSeminar.exception.model;

import sopt.org.fouthSeminar.exception.Error;

public class BadRequestException extends SoptException{
    public BadRequestException(Error error, String message) {
        super(error, message);
    }
}
