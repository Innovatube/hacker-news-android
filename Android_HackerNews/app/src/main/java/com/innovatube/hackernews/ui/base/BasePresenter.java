package com.innovatube.hackernews.ui.base;

/**
 * Created by Thanh on 05/09/2016.
 */
public class BasePresenter<V extends BaseViewInterface> implements BasePresenterInterface<V> {

    private V viewInterface;

    @Override
    public void attachView(V viewInterface) {
        this.viewInterface = viewInterface;
    }

    @Override
    public void detachView() {
        viewInterface = null;
    }

    public boolean isViewAttached() {
        return viewInterface != null;
    }

    public V getViewInterface() {
        return viewInterface;
    }
}
