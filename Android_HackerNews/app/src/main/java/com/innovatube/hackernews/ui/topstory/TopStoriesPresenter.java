package com.innovatube.hackernews.ui.topstory;

import android.util.Log;

import com.innovatube.hackernews.data.DataManager;
import com.innovatube.hackernews.data.model.Story;
import com.innovatube.hackernews.ui.base.BasePresenter;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TopStoriesPresenter extends BasePresenter<TopStoriesViewInterface> {
    private DataManager dataManager;
    private Retrofit retrofit;
    private Subscription subscription;

    @Inject
    public TopStoriesPresenter(DataManager dataManager, Retrofit retrofit) {
        this.dataManager = dataManager;
        this.retrofit = retrofit;
    }

    @Override
    public void detachView() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
        super.detachView();
    }

    public void getStoryId() {
        dataManager.getStoryId()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Story>() {
                    @Override
                    public void onCompleted() {
                        Log.w("onCompleted", "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError", e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(Story story) {
                        getViewInterface().addStoryItemToAdapter(story);
                    }
                });
    }

    public void saveStoryUserChoose(Story story) {
        Log.e("saveStoryUserChoose", "vao");
    }
}
