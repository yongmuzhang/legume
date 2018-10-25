package org.zym.soybean.config.validator;

import org.zym.soybean.config.validator.annotation.Enumeration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 描述 : 枚举值的校验
 *
 * @author zym
 * @date 18/5/14
 */
public class EnumerationValidator implements ConstraintValidator<Enumeration, String> {

    private Set<String> allowedValueSet;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void initialize(Enumeration constraintAnnotation) {
        Class<? extends Enum> enumSelected = constraintAnnotation.targetClassType();
        allowedValueSet = (Set<String>) EnumSet
                .allOf(enumSelected).stream()
                .map(e -> ((Enum<? extends Enum<?>>) e).name())
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (!Optional.ofNullable(s).isPresent() || allowedValueSet.contains(s)) {
            return true;
        } else {
            return false;
        }
    }
}
