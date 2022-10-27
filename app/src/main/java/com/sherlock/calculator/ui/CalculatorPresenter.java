package com.sherlock.calculator.ui;

import com.sherlock.calculator.model.Calculator;
import com.sherlock.calculator.model.Operator;
import com.sherlock.calculator.model.PlusMinus;

import java.text.DecimalFormat;

public class CalculatorPresenter {
    private CalculatorView view;
    private Calculator calculator;

    private double argOne;
    private Double argTwo;
    private Double argRes;

    private Operator selectedOperator;

    private DecimalFormat formater = new DecimalFormat("#.####");

    private PlusMinus plusMinus = PlusMinus.PLUS;

    private boolean dotPressed;
    private int numberDigit;

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }


    public void onDigitPressed(int digit) {
        if (argTwo == null) {
            if(dotPressed) {
                argOne = argOne + (double) (digit)/numberDigit;
                numberDigit=numberDigit*10;
            }else{
                argOne = argOne * 10 + digit;
            }
            showFormatted(argOne);
        }else{
            if(dotPressed) {
                argTwo = argTwo + (double) (digit)/numberDigit;
                numberDigit=numberDigit*10;
            }else{
                argTwo = argTwo * 10 + digit;
            }
            showFormatted(argTwo);
        }
        argRes = null;
    }

    public void onOperatorPressed(Operator operator) {
        //if(selectedOperator!=null){
        if(argRes!=null){
            argOne = argRes;
            argRes = null;
        } else if(argTwo!=null){
            argOne = calculator.perform(argOne,argTwo,selectedOperator);
            showFormatted(argOne);
        }

        dotPressed = false;
        argTwo=0.0;
        selectedOperator = operator;
    }

    public void onDotPressed() {
        if(!dotPressed) {
            dotPressed = true;
            numberDigit = 10;
        }
    }

    private void showFormatted(double d){
        view.showResult(formater.format(d));
    }

    public void onKeyResultPressed() {
        if(selectedOperator!=Operator.RES){
            if (argTwo!=null) {
                argOne = calculator.perform(argOne, argTwo, selectedOperator);
                showFormatted(argOne);
                argRes = argOne;
                argOne = 0;
                argTwo = null;
                selectedOperator = Operator.RES;
                dotPressed = false;
            }
        }
    }

    public void onKeyClearResultPressed() {
        argOne = 0.0;
        argTwo = null;
        dotPressed = false;
        showFormatted(argOne);
    }

    public void onKeyPercentPressed() {
        argTwo = calculator.percent(argOne,argTwo);
        onKeyResultPressed();
    }

    public void onKeyPlusMinusPressed() {
        if(argTwo==null){
            argOne = -argOne;
            showFormatted(argOne);
        }else {
            argTwo = - argTwo;
            showFormatted(argTwo);
        }
    }
}
