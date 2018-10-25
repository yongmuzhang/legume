package org.zym.soybean.config.validator.annotation;

import org.zym.soybean.config.validator.MoneyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zym
 * @date 18/5/14
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MoneyValidator.class)
public @interface Money {
    String message() default "不是合法的金额格式";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
