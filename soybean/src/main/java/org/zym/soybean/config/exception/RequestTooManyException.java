package org.zym.soybean.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.zym.soybean.config.exception.BaseException;

/**
 * @author zym
 * @date 18/4/22
 */
@ResponseStatus(value = HttpStatus.TOO_MANY_REQUESTS)
public class RequestTooManyException extends BaseException {
  public RequestTooManyException(String message, String errorCode) {
    super(message, errorCode);
  }
}
