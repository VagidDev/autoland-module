package com.college.controller.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateFormatValidator<T, R> implements Validator<T, R> {
    private final static SimpleDateFormat SLASH_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private final Date lowestDate;

    public DateFormatValidator() {
        try {
            lowestDate = SLASH_DATE_FORMAT.parse("01/01/1900");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public R validate(T t) {
        Date date = getDate(t);
        if (date == null) {
            return getInvalidResponse();
        } else if (date.before(lowestDate)) {
            return getInvalidResponse();
        }
        return getValidResponse();
    }


    protected abstract Date getDate(T t);
    protected abstract R getValidResponse();
    protected abstract R getInvalidResponse();
}
