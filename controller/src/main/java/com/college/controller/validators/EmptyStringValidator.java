package com.college.controller.validators;

public abstract class EmptyStringValidator<T, R> implements Validator<T, R> {
    public R validate(T t) {
        String stringToValidate = getStringToValidate(t);
        if (stringToValidate == null) {
            return getInvalidResponse();
        }
        stringToValidate = stringToValidate.trim();
        if (stringToValidate.isEmpty()) {
            return getInvalidResponse();
        }
        return getValidResponse();
    }

    protected abstract String getStringToValidate(T t);
    protected abstract R getValidResponse();
    protected  abstract R getInvalidResponse();
}
