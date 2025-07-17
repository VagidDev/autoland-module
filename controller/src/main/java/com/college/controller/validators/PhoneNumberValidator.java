package com.college.controller.validators;

public abstract class PhoneNumberValidator<T, R> extends EmptyStringValidator<T, R> {
    @Override
    public R validate(T t) {
        if (super.validate(t) != getValidResponse())
            return getInvalidResponse();

        String regexp = "^\\+373\\d{8}$";
        String value = getStringToValidate(t);
        if (!value.matches(regexp)) {
            return getInvalidResponse();
        }

        return getValidResponse();
    }
}
