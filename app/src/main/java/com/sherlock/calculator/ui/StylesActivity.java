package com.sherlock.calculator.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.radiobutton.MaterialRadioButton;
import com.sherlock.calculator.R;

public class StylesActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_styles);
        //initThemeChooser();
    }

/*
    private void initThemeChooser(){
        initRadioButton(findViewById(R.id.radioButtonMyCoolStyle), MyCoolCodeStyle);
        initRadioButton(findViewById(R.id.radioButtonMaterialDark), AppThemeDarkCodeStyle);
        initRadioButton(findViewById(R.id.radioButtonMaterialLight), AppThemeLightCodeStyle);
        initRadioButton(findViewById(R.id.radioButtonMaterialLightDarkAction), AppThemeCodeStyle);

        RadioGroup rg = findViewById(R.id.radioButtons);
        ((MaterialRadioButton) rg.getChildAt(getCodeStyle(MyCoolCodeStyle))).setChecked(true);
    }

    private void initRadioButton(View button, final int codeStyle){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAppTheme(codeStyle);
                recreate();
            }
        });
    }

 */
}
