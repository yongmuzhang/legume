package org.zym.soybean.config.validator;

import org.zym.soybean.config.validator.annotation.Money;
import org.zym.soybean.constant.Constant;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * 描述 : 金额的校验
 *
 * @author zym
 * @date 18/5/14
 */
public class MoneyValidator implements ConstraintValidator<Money, Double> {

    private Pattern moneyPattern = Pattern.compile(Constant.MONEY_REGEX);

    @Override
    public void initialize(Money constraintAnnotation) {

    }

    @Override
    public boolean isValid(Double aDouble, ConstraintValidatorContext constraintValidatorContext) {
        if (Optional.ofNullable(aDouble).isPresent()) {
            return moneyPattern.matcher(aDouble.toString()).matches();
        } else {
            return false;
        }
    }
}
