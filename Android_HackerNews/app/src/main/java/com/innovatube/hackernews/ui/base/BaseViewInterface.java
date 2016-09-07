package com.innovatube.hackernews.ui.base;

public interface BaseViewInterface {
    void createProgressDialog();

    void createAlertDialog();

    void showProgressDialog(boolean value);

    void showAlertDialog(String errorMessage);

    void dismissDialog();
}
