package com.college.controller.validators;

public interface Validator<T, R> {
    R validate(T t);
}
