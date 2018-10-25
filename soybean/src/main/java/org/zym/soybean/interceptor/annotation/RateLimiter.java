package org.zym.soybean.interceptor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zym
 * @date 18/6/18
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimiter {

  /**
   * 请求url
   */
  String url();

  /**
   * 允许访问的次数，默认值MAX_VALUE
   */
  int count() default Integer.MAX_VALUE;

  /**
   * 时间段，单位为毫秒，默认值一分钟
   */
  long time() default 60000;
}
