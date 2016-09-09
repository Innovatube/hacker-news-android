package com.innovatube.hackernews.ui.storydetail;

import com.innovatube.hackernews.data.DataManager;
import com.innovatube.hackernews.ui.base.BasePresenter;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Subscription;

/**
 * Created by Thanh on 09/09/2016.
 */
public class StoryDetailPresenter extends BasePresenter<StoryDetailViewInterface> {
    private DataManager dataManager;
    private Subscription subscription;

    @Inject
    public StoryDetailPresenter(DataManager dataManager, Retrofit retrofit) {
        this.dataManager = dataManager;
    }

    @Override
    public void detachView() {
        super.detachView();

        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    public String getUrl() {
        return dataManager.getUrl();
    }
}
