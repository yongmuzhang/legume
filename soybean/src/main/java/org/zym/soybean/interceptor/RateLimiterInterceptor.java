package org.zym.soybean.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.zym.soybean.config.exception.RequestTooManyException;
import org.zym.soybean.interceptor.annotation.RateLimiter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author zym
 * @date 18/6/18
 */
@Component
public class RateLimiterInterceptor extends HandlerInterceptorAdapter {

  @Autowired
  private RedisTemplate redisTemplate;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (!(handler instanceof HandlerMethod)) {
      return true;
    }
    HandlerMethod handlerMethod = (HandlerMethod) handler;
    Method method = handlerMethod.getMethod();
    RateLimiter rateLimiter = method.getAnnotation(RateLimiter.class);
    if (Optional.ofNullable(rateLimiter).isPresent()) {
      boolean allowRequest = checkRequestLimit(rateLimiter);
      if (allowRequest) {
        return true;
      } else {
        throw new RequestTooManyException("请求频繁，请稍后","");
      }
    } else {
      return true;
    }
  }

  /**
   * @param limit
   * @return
   */
  private boolean checkRequestLimit(RateLimiter limit) {
    String key = limit.url();
    long incrementStep = 1L;
    Long requestedTimes = redisTemplate.opsForValue().increment(key, incrementStep);
    Long leftExpireTime = redisTemplate.getExpire(key);
    if (!Optional.ofNullable(leftExpireTime).isPresent()) {
      redisTemplate.expire(key, limit.time(), TimeUnit.MILLISECONDS);
    }
    if (Optional.ofNullable(requestedTimes).isPresent() && requestedTimes > limit.count()) {
      return false;
    } else {
      return true;
    }
  }
}
