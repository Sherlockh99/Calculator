package com.sherlock.calculator.ui;

import android.icu.number.NumberFormatter;

import com.sherlock.calculator.model.Calculator;
import com.sherlock.calculator.model.Operator;

import java.text.DecimalFormat;

public class CalculatorPresenter {
    private CalculatorView view;
    private Calculator calculator;

    private double argOne;
    private Double argTwo;
    private Double argRes;

    private Operator selectedOperator;

    private DecimalFormat formater = new DecimalFormat("#.##");

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }


    public void onDigitPressed(int digit) {
        if (argTwo == null) {
            argOne = argOne * 10 + digit;
            showFormatted(argOne);
        }else {
            argTwo = argTwo*10 + digit;
            showFormatted(argTwo);
        }
        argRes = null;
    }

    public void onOperatorPressed(Operator operator) {
        //if(selectedOperator!=null){
        if(argRes!=null){
            argOne = argRes;
            argRes = null;
        }else if(argTwo!=null){
            argOne = calculator.perform(argOne,argTwo,selectedOperator);
            showFormatted(argOne);
        }
        argTwo=0.0;
        selectedOperator = operator;
    }

    public void onDotPressed() {

    }

    private void showFormatted(double d){
        view.showResult(formater.format(d));
    }

    public void onKeyResultPressed() {
        if(selectedOperator!=Operator.RES){
            argOne = calculator.perform(argOne,argTwo,selectedOperator);
            showFormatted(argOne);
            argRes = argOne;
            argOne = 0;
            argTwo = null;
            selectedOperator = Operator.RES;
        }
    }

    public void onKeyClearResultPressed() {
        argOne = 0.0;
        argTwo = null;
        showFormatted(argOne);
    }

    public void onKeyPercentPressed() {
        argTwo = calculator.percent(argOne,argTwo);
        onKeyResultPressed();
    }
}
