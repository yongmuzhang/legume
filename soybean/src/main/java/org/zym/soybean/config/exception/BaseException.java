package org.zym.soybean.config.exception;

import lombok.Data;

/**
 * @author zym
 * @date 18/4/22
 */
@Data
public abstract class BaseException extends RuntimeException{

    private String errorCode;
    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BaseException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public BaseException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
                         String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }
}
