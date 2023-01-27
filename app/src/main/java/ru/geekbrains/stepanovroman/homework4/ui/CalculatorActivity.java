package ru.geekbrains.stepanovroman.homework4.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import ru.geekbrains.stepanovroman.homework4.R;
import ru.geekbrains.stepanovroman.homework4.model.CalculatorImpl;
import ru.geekbrains.stepanovroman.homework4.model.Operator;
import ru.geekbrains.stepanovroman.homework4.model.Theme;
import ru.geekbrains.stepanovroman.homework4.model.ThemeRepository;
import ru.geekbrains.stepanovroman.homework4.model.ThemeRepositoryImpl;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private static final String KEY_ARGUMENT = "argument";

    private TextView resultTxt;

    private CalculatorPresenter presenter;

    private ThemeRepository themeRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        themeRepository = ThemeRepositoryImpl.getInstance(this);

        setTheme(themeRepository.getSavedTheme().getThemeRes());

        setContentView(R.layout.activity_calculator);

        resultTxt = findViewById(R.id.result);

        presenter = new CalculatorPresenter(this, new CalculatorImpl());

        if (savedInstanceState != null) {
            presenter.setArgOne(savedInstanceState.getDouble(KEY_ARGUMENT));
        }



        Map<Integer, Integer> digits = new HashMap<>();
        digits.put(R.id.button_1, 1);
        digits.put(R.id.button_2, 2);
        digits.put(R.id.button_3, 3);
        digits.put(R.id.button_4, 4);
        digits.put(R.id.button_5, 5);
        digits.put(R.id.button_6, 6);
        digits.put(R.id.button_7, 7);
        digits.put(R.id.button_8, 8);
        digits.put(R.id.button_9, 9);
        digits.put(R.id.button_0, 0);

        View.OnClickListener digitClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDigitPressed(digits.get(view.getId()));
            }
        };

        findViewById(R.id.button_1).setOnClickListener(digitClickListener);
        findViewById(R.id.button_2).setOnClickListener(digitClickListener);
        findViewById(R.id.button_3).setOnClickListener(digitClickListener);
        findViewById(R.id.button_4).setOnClickListener(digitClickListener);
        findViewById(R.id.button_5).setOnClickListener(digitClickListener);
        findViewById(R.id.button_6).setOnClickListener(digitClickListener);
        findViewById(R.id.button_7).setOnClickListener(digitClickListener);
        findViewById(R.id.button_8).setOnClickListener(digitClickListener);
        findViewById(R.id.button_9).setOnClickListener(digitClickListener);
        findViewById(R.id.button_0).setOnClickListener(digitClickListener);

        Map<Integer, Operator> operators = new HashMap<>();
        operators.put(R.id.button_plus, Operator.ADD);
        operators.put(R.id.button_minus, Operator.SUB);
        operators.put(R.id.button_multiply, Operator.MULT);
        operators.put(R.id.button_divide, Operator.DIV);
        operators.put(R.id.button_equals, Operator.EQU);


        View.OnClickListener operatorsClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onOperatorPressed(operators.get(view.getId()));
            }
        };

        findViewById(R.id.button_plus).setOnClickListener(operatorsClickListener);
        findViewById(R.id.button_minus).setOnClickListener(operatorsClickListener);
        findViewById(R.id.button_multiply).setOnClickListener(operatorsClickListener);
        findViewById(R.id.button_divide).setOnClickListener(operatorsClickListener);
        findViewById(R.id.button_equals).setOnClickListener(operatorsClickListener);


        findViewById(R.id.button_point).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onPointPressed();
            }
        });


        findViewById(R.id.button_theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalculatorActivity.this, SelectThemeActivity.class);
                intent.putExtra(SelectThemeActivity.EXTRA_THEME,themeRepository.getSavedTheme());
                startActivity(intent);
            }
        });



    }

    @Override
    public void showResult(String result) {
        resultTxt.setText(result);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putDouble(KEY_ARGUMENT, presenter.getArgOne());
        super.onSaveInstanceState(outState);
    }
}