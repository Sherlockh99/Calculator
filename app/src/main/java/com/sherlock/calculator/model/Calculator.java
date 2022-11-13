package com.sherlock.calculator.model;

public interface Calculator {
    double perform(double argOne, double argTwo, Operator firstOperator);

    double percent(double argOne, double argTwo);
}
