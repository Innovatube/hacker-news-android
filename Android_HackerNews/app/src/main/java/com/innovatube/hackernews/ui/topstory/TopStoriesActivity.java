package com.innovatube.hackernews.ui.topstory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.innovatube.hackernews.R;
import com.innovatube.hackernews.data.model.Story;
import com.innovatube.hackernews.eventbus.RxEventBus;
import com.innovatube.hackernews.eventbus.event.ItemClickEvent;
import com.innovatube.hackernews.ui.base.BaseActivity;
import com.innovatube.hackernews.ui.storydetail.StoryDetailActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TopStoriesActivity extends BaseActivity implements TopStoriesViewInterface,
        SwipeRefreshLayout.OnRefreshListener {
    StoryAdapter adapter;

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.rvListTopStory)
    RecyclerView listTopStory;

    @Inject
    TopStoriesPresenter presenter;

    boolean clickTopStory = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_news);
        ButterKnife.bind(this);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_light);
        getActivityComponent().inject(this);

        presenter.attachView(this);
        adapter = new StoryAdapter();
        setupRecyclerView();
        presenter.getTopStoryId();
        getSupportActionBar().setTitle(getResources().getString(R.string.top_stories));
    }

    private void setupRecyclerView() {
        listTopStory.setLayoutManager(new LinearLayoutManager(this));
        listTopStory.setHasFixedSize(true);
        listTopStory.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("onResume", "onResume");
        RxEventBus.getInstance().toObserverable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @OnClick(R.id.btnTopStories)
    public void OnButtonTopStoriesClick() {
        if (clickTopStory) {
            return;
        } else {
            getSupportActionBar().setTitle(getResources().getString(R.string.top_stories));
            clickTopStory = true;
            adapter.setItems(new ArrayList<Story>());
            presenter.getTopStoryId();
        }
    }

    @OnClick(R.id.btnNewStories)
    public void OnButtonNewStoriesClick() {
        if (clickTopStory) {
            getSupportActionBar().setTitle(getResources().getString(R.string.new_stories));
            clickTopStory = false;
            adapter.setItems(new ArrayList<Story>());
            presenter.getNewStoryId();
        } else {
            return;
        }
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
        subscriber.unsubscribe();
        super.onDestroy();
    }

    @Override
    public void addStoryItemToAdapter(Story story) {
        adapter.addItem(story);
    }

    private Subscriber<? super Object> subscriber = new Subscriber<Object>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(Object event) {
            if (event instanceof ItemClickEvent) {
                int p = ((ItemClickEvent) event).getPosition();
                presenter.saveUrl(adapter.getStoryList().get(p).getUrl());
                gotoStoryDetailScreen();
            }
        }
    };

    private void gotoStoryDetailScreen() {
        startActivity(new Intent(this, StoryDetailActivity.class));
    }

    @Override
    public void onRefresh() {
        if (adapter != null) {
            adapter.setItems(new ArrayList<Story>());
            if (clickTopStory) {
                presenter.getTopStoryId();
            } else {
                presenter.getNewStoryId();
            }

        }
    }

    @Override
    public void hideLoadingViews() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
