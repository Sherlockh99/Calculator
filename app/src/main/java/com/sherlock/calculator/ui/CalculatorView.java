package com.sherlock.calculator.ui;

import java.io.Serializable;

public interface CalculatorView extends Serializable {
    void showResult(String result);
}
