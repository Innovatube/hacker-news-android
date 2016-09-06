package com.innovatube.hackernews.ui.topstory;

import android.os.Bundle;

import com.innovatube.hackernews.R;
import com.innovatube.hackernews.ui.base.BaseActivity;

import javax.inject.Inject;

public class TopStoriesActivity extends BaseActivity implements TopStoriesViewInterface {

    @Inject
    TopStoriesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_news);
        getActivityComponent().inject(this);
        presenter.attachView(this);

        presenter.getStoryId();
    }

    @Override
    public void createProgressDialog() {

    }

    @Override
    public void createAlertDialog() {

    }

    @Override
    public void showProgressDialog(boolean value) {

    }

    @Override
    public void showAlertDialog(String errorMessage) {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.detachView();
        }
        dismissDialog();
        super.onDestroy();
    }
}
