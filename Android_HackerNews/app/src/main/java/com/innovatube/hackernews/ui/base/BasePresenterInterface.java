package com.innovatube.hackernews.ui.base;

/**
 * Created by Thanh on 05/09/2016.
 */
public interface BasePresenterInterface<V extends BaseViewInterface> {
    void attachView(V mvpView);

    void detachView();
}
