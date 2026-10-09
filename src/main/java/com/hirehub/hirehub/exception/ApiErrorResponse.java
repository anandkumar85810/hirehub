package com.hirehub.hirehub.exception;

public record ApiErrorResponse(int status, String error, String message, String path) {
}
