package com.sherlock.calculator.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sherlock.calculator.R;
import com.sherlock.calculator.model.CalculatorImpl;
import com.sherlock.calculator.model.Operator;

import java.util.HashMap;
import java.util.Map;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private static final String KEY_RESULT = "KEY_RESULT";
    private final String KEY_PRESENTER = "KEY_PRESENTER";
    private TextView resultTxt;
    private CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        resultTxt = findViewById(R.id.result);

        if(savedInstanceState!=null) {
            presenter = (CalculatorPresenter) savedInstanceState.getSerializable(KEY_PRESENTER);
            presenter.setView(this);
            showResult(savedInstanceState.getString(KEY_RESULT));
        } else{
            presenter = new CalculatorPresenter(this, new CalculatorImpl());
        }

        Map<Integer,Integer> digits = new HashMap<>();
        digits.put(R.id.key_0,0);
        digits.put(R.id.key_1,1);
        digits.put(R.id.key_2,2);
        digits.put(R.id.key_3,3);
        digits.put(R.id.key_4,4);
        digits.put(R.id.key_5,5);
        digits.put(R.id.key_6,6);
        digits.put(R.id.key_7,7);
        digits.put(R.id.key_8,8);
        digits.put(R.id.key_9,9);

        View.OnClickListener digitClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDigitPressed(digits.get(view.getId()));
            }
        };

        findViewById(R.id.key_0).setOnClickListener(digitClickListener);
        findViewById(R.id.key_1).setOnClickListener(digitClickListener);
        findViewById(R.id.key_2).setOnClickListener(digitClickListener);
        findViewById(R.id.key_3).setOnClickListener(digitClickListener);
        findViewById(R.id.key_4).setOnClickListener(digitClickListener);
        findViewById(R.id.key_5).setOnClickListener(digitClickListener);
        findViewById(R.id.key_6).setOnClickListener(digitClickListener);
        findViewById(R.id.key_7).setOnClickListener(digitClickListener);
        findViewById(R.id.key_8).setOnClickListener(digitClickListener);
        findViewById(R.id.key_9).setOnClickListener(digitClickListener);

        Map<Integer, Operator> operators = new HashMap<>();
        operators.put(R.id.key_plus,Operator.ADD);
        operators.put(R.id.key_minus,Operator.SUB);
        operators.put(R.id.key_mult,Operator.MULT);
        operators.put(R.id.key_div,Operator.DIV);

        View.OnClickListener operatorsClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onOperatorPressed(operators.get(view.getId()));
            }
        };

        findViewById(R.id.key_plus).setOnClickListener(operatorsClickListener);
        findViewById(R.id.key_minus).setOnClickListener(operatorsClickListener);
        findViewById(R.id.key_mult).setOnClickListener(operatorsClickListener);
        findViewById(R.id.key_div).setOnClickListener(operatorsClickListener);

        findViewById(R.id.key_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDotPressed();
            }
        });

        findViewById(R.id.key_result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onKeyResultPressed();
            }
        });

        findViewById(R.id.key_clear_result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onKeyClearResultPressed();
            }
        });

        findViewById(R.id.key_percent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onKeyPercentPressed();
            }
        });

        findViewById(R.id.key_plus_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onKeyPlusMinusPressed();
            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(KEY_PRESENTER, presenter);
        outState.putString(KEY_RESULT,resultTxt.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void showResult(String result) {
        resultTxt.setText(result);
    }
}