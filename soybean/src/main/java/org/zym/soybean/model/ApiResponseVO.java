package org.zym.soybean.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zym
 * @date 18/8/13 下午5:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseVO<T> {
  private String code;
  private String errorCode;
  private String errorMessage;
  private String exception;
  private Integer status;
  private T result;

  public ApiResponseVO(String code, T result) {
    this.code = code;
    this.result = result;
  }

  public ApiResponseVO(String code) {
    this.code = code;
  }

  public ApiResponseVO(String code, String errorCode, String errorMessage, String exception, Integer status) {
    this.code = code;
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.exception = exception;
    this.status = status;
  }
}
