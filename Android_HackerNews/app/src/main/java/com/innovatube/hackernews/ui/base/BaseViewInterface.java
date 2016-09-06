package com.innovatube.hackernews.ui.base;

/**
 * Created by Thanh on 05/09/2016.
 */
public interface BaseViewInterface {
    void createProgressDialog();

    void createAlertDialog();

    void showProgressDialog(boolean value);

    void showAlertDialog(String errorMessage);

    void dismissDialog();
}
