package com.wang.mall.exception;

import com.wang.mall.api.IErrorCode;

public class ApiException extends RuntimeException {
    private IErrorCode iErrorCode;

    public ApiException(IErrorCode iErrorCode){
        super((iErrorCode.getMessage()));
        this.iErrorCode = iErrorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode(){
        return iErrorCode;
    }
}
