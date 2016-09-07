package com.innovatube.hackernews.ui.base;

public interface BasePresenterInterface<V extends BaseViewInterface> {
    void attachView(V mvpView);

    void detachView();
}
