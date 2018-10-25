package org.zym.soybean.config.exception;

import org.zym.soybean.config.exception.BaseException;

/**
 * @author zym
 * @date 18/8/23 下午2:44
 */
public class DatabaseOperationException extends BaseException {
    public DatabaseOperationException(String message) {
        super(message);
    }
}
