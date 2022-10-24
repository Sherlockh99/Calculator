package com.sherlock.calculator.model;

public class CalculatorImpl implements Calculator {
    @Override
    public double perform(double argOne, double argTwo, Operator firstOperator) {

        switch (firstOperator){
            case ADD:
                return argOne + argTwo;
            case SUB:
                return argOne - argTwo;
            case MULT:
                return argOne * argTwo;
            case DIV:
                return argOne / argTwo;
        }
        return argOne;
    }

    @Override
    public double percent(double argOne, double argTwo) {
        return argOne * argTwo / 100;
    }
}
