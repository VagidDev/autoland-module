package com.college.controller.validators;

public abstract class EmptyStringValidator<T, R> implements Validator<T, R> {
    public R validate(T t) {
        String stringToValidate = getStringToValidate(t);
        if (stringToValidate == null || stringToValidate.isEmpty()) {
            return getValidResponse();
        }
        return getInvalidResponse();
    }

    protected abstract String getStringToValidate(T t);
    protected abstract R getValidResponse();
    protected  abstract R getInvalidResponse();
}
