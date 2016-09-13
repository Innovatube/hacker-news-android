package com.innovatube.hackernews.ui.base;

public interface BaseViewInterface extends MvpView {
    void createProgressDialog();

    void createAlertDialog();

    void showProgressDialog(boolean value);

    void showAlertDialog(String errorMessage);

    void dismissDialog();
}
