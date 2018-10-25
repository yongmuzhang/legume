package org.zym.soybean.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author zym
 * @date 18/4/22
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AuthorizationException extends BaseException {
  public AuthorizationException(String message, String errorCode) {
    super(message, errorCode);
  }
}
