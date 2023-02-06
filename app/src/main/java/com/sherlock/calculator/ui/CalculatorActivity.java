package com.sherlock.calculator.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sherlock.calculator.R;
import com.sherlock.calculator.model.CalculatorImpl;
import com.sherlock.calculator.model.Operator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private static final String KEY_RESULT = "KEY_RESULT";
    private final String KEY_PRESENTER = "KEY_PRESENTER";
    private TextView resultTxt;
    private CalculatorPresenter presenter;

    static final String NameSharedPreference = "CALCULATOR";
    final String appTheme = "APP_THEME";
    private int i;
    final int AppThemeLightCodeStyle = 1;
    final int AppThemeCodeStyle = 2;
    final int AppThemeDarkCodeStyle = 3;
    private int[] themes = new int[]{AppThemeCodeStyle,AppThemeCodeStyle,AppThemeDarkCodeStyle};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.MyColorStyle));
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


        findViewById(R.id.key_changeTheme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i>=themes.length){
                    i=0;
                }else {
                    i++;
                }
                setAppTheme(i);
                recreate();
            }
        });
        //i = 0;
    }

    //сохранение настроек
    void setAppTheme(int codeStyle){
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        //настройки сохраняются посредством специального класса editor
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(appTheme,codeStyle);
        editor.apply();
    }

    private int getAppTheme(int codeStyle){
        int index = getCodeStyle(codeStyle);
        //i = Arrays.asList(codeStyle).indexOf(index);
        i = index;
        return codeStyleToStyleId(index);
    }

    private int codeStyleToStyleId(int codeStyle){
        switch (codeStyle){
            case AppThemeCodeStyle:
                return R.style.Theme_Calculator;
            case AppThemeLightCodeStyle:
                return R.style.AppThemeLight;
            case AppThemeDarkCodeStyle:
                return R.style.AppThemeDark;
            default:
                return R.style.MyColorStyle;
        }
    }

    //Чтение настроек, параметр Тема
    int getCodeStyle(int codeStyle){
        //Работаем через специальный класс сохранения и чтения настроек
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        //прочитать тему, если настройка не найдена - взять по умолчанию
        return sharedPref.getInt(appTheme, codeStyle);
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