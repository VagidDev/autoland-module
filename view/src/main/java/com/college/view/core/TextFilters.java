package com.college.view.core;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public final class TextFilters {
    // first version of filter
    public static final UnaryOperator<TextFormatter.Change> LENGTH_FILTER = change -> {
        if (change.getControlNewText().length() <= 12) {
            return change;
        }
        return null;
    };

    public static final UnaryOperator<TextFormatter.Change> NUMBER_FILTER = change -> {
        String pattern = "^\\+\\d{0,11}$";
        String text = change.getControlNewText();

        if (text.matches(pattern)) {
            return change;
        }
        return null;
    };

}
