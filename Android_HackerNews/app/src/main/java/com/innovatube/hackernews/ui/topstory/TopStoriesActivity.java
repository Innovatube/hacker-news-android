package com.innovatube.hackernews.ui.topstory;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.innovatube.hackernews.R;
import com.innovatube.hackernews.data.model.Story;
import com.innovatube.hackernews.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopStoriesActivity extends BaseActivity implements TopStoriesViewInterface {
    StoryAdapter adapter;

    @BindView(R.id.rvListTopStory)
    RecyclerView listTopStory;

    @Inject
    TopStoriesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_news);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);

        presenter.attachView(this);
        adapter = new StoryAdapter();
        setupRecyclerView();
        presenter.getStoryId();
    }

    private void setupRecyclerView() {
        listTopStory.setLayoutManager(new LinearLayoutManager(this));
        listTopStory.setHasFixedSize(true);
        listTopStory.setAdapter(adapter);

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

    @Override
    public void addStoryItemToAdapter(Story story) {
        adapter.addItem(story);
    }
}
