package org.zym.soybean.config.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.zym.soybean.config.exception.BaseException;
import org.zym.soybean.constant.ApiResponseCode;
import org.zym.soybean.model.ApiResponseVO;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author zym
 * @date 18/4/22
 */
@RestControllerAdvice
@ConditionalOnProperty(name = "soybean.exception.enabled", havingValue = "true", matchIfMissing = true)
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);

    @ExceptionHandler(BaseException.class)
    ResponseEntity<?> handleControllerBaseException(HttpServletRequest request, BaseException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Optional<ResponseStatus> optionalResponseStatus = Optional.ofNullable(
                ex.getClass().getAnnotation(ResponseStatus.class));
        if (optionalResponseStatus.isPresent()) {
            status = optionalResponseStatus.get().value();
        }
        String errorMessage = ex.getMessage();
        String errorCode = ex.getErrorCode();
        String exceptionName = ex.getClass().getName();
        int errorStatus = status.value();
        ApiResponseVO errorResult = new ApiResponseVO(ApiResponseCode.FAIL_CODE, errorCode, errorMessage, exceptionName,
                errorStatus);
        ResponseEntity responseEntity = new ResponseEntity<>(errorResult, status);
        logger.error("", ex);
        return responseEntity;
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        String errorMessage = ex.getMessage();
        String errorCode = "UNKNOWN";
        String exceptionName = ex.getClass().getName();
        int errorStatus = status.value();
        ApiResponseVO errorResult = new ApiResponseVO(ApiResponseCode.FAIL_CODE, errorCode, errorMessage, exceptionName,
                errorStatus);
        ResponseEntity responseEntity = new ResponseEntity<>(errorResult, status);
        return responseEntity;
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<?> handleControllerException(Exception ex, WebRequest request) throws Exception {
        logger.error("", ex);
        try {
            ResponseEntity<Object> objectResponseEntity = this.handleException(ex, request);
            return objectResponseEntity;
        } catch (Exception e) {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            ApiResponseVO errorResult = new ApiResponseVO(ApiResponseCode.FAIL_CODE, "UNKNOWN",
                    ex.getMessage(), ex.getClass().getName(), status.value());
            ResponseEntity responseEntity = new ResponseEntity<>(errorResult, status);
            return responseEntity;
        }
    }

    @ExceptionHandler(Throwable.class)
    ResponseEntity<?> handleControllerThrowable(HttpServletRequest request, Throwable throwable) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String errorMessage = throwable.getMessage();
        String errorCode = "UNKNOWN";
        String exceptionName = throwable.getClass().getName();
        int errorStatus = status.value();
        ApiResponseVO errorResult = new ApiResponseVO(ApiResponseCode.FAIL_CODE, errorCode, errorMessage, exceptionName,
                errorStatus);
        logger.error("", throwable);
        ResponseEntity responseEntity = new ResponseEntity<>(errorResult, status);
        return responseEntity;
    }
}
