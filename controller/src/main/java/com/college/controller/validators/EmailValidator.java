package com.college.controller.validators;

import java.util.Arrays;

public abstract class EmailValidator<T, R> implements Validator<T, R> {
    private String[] patterns = {
            "@gmail.com",
            "@yahoo.com",
            "@email.com",
            "@mail.ru",
            "@yandex.ru",
            "@hotmail.com",
            "@hotmail.ru",
            "@inbox.ru",
            "@outlook.com"
    };

    @Override
    public R validate(T t) {
        String str = getStringToValidate(t);

        if (str == null) {
            return getInvalidResponse();
        }

        boolean isEmail = Arrays.stream(patterns)
                .anyMatch(pattern -> str.endsWith(pattern)
                        && str.length() > pattern.length());
        if (isEmail) {
            return getValidResponse();
        }
        return getInvalidResponse();
    }


    protected abstract String getStringToValidate(T t);
    protected abstract R getValidResponse();
    protected abstract R getInvalidResponse();
}
