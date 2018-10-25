package org.zym.soybean.config.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zym
 * @date 18/4/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResult implements Serializable {
    private String code;
    private String errorCode;
    private String errorMessage;
    private String exception;
    private Integer status;
}
