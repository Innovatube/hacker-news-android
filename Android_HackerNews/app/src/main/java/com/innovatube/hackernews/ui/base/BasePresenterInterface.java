package com.innovatube.hackernews.ui.base;

public interface BasePresenterInterface<V extends MvpView> {
    void attachView(V mvpView);

    void detachView();
}
