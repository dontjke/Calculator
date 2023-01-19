package ru.geekbrains.stepanovroman.homework4.ui;



import java.text.DecimalFormat;

import ru.geekbrains.stepanovroman.homework4.model.Calculator;
import ru.geekbrains.stepanovroman.homework4.model.Operator;

public class CalculatorPresenter {

    private final DecimalFormat format = new DecimalFormat("#.##");

    private final CalculatorView view;
    private final Calculator calculator;

    public double getArgOne() {
        return argOne;
    }

    public void setArgOne(double argOne) {
        this.argOne = argOne;
        showFormatted(argTwo == null ? argOne : argTwo);
    }


    private double argOne;
    private Double argTwo;
    private Operator selectedOperator;

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    public void onDigitPressed(int digit) {
        if (argTwo == null) {
            argOne = argOne * 10 + digit;
            showFormatted(argOne);
        } else {
            argTwo = argTwo * 10 + digit;
            showFormatted(argTwo);
        }
    }

    public void onOperatorPressed(Operator operator) {
        if (selectedOperator != null) {
            argOne = calculator.perform(argOne, argTwo, selectedOperator);
            showFormatted(argOne);
        }
        argTwo = 0.0;

        selectedOperator = operator;
    }

    public void onPointPressed() {

    }

    private void showFormatted(double value) {
        view.showResult(format.format(value));
    }

}
