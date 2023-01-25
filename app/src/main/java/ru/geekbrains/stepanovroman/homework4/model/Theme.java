package ru.geekbrains.stepanovroman.homework4.model;

import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import ru.geekbrains.stepanovroman.homework4.R;

public enum Theme {
    ONE(R.style.Theme_Homework4, R.string.theme_one, "theme_one"),
    TWO(R.style.ThemeCalculatorV2, R.string.theme_two, "theme_two"),
    THREE(R.style.ThemeCalculatorV3, R.string.theme_three, "theme_three");

    @StyleRes
    private final int themeRes;

    @StringRes
    private final int title;

    private final String key;

    Theme(int themeRes, int title, String key) {
        this.themeRes = themeRes;
        this.title = title;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public int getTitle() {
        return title;
    }

    public int getThemeRes() {
        return themeRes;
    }
}
