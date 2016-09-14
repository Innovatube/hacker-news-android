package com.innovatube.hackernews.data;

import com.innovatube.hackernews.data.local.PreferenceHelper;
import com.innovatube.hackernews.data.local.RealmHelper;
import com.innovatube.hackernews.data.model.Story;
import com.innovatube.hackernews.data.remote.InnovatubeService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import rx.Observable;
import rx.functions.Func1;

@Singleton
public class DataManager {
    private final InnovatubeService service;
    private final PreferenceHelper preferenceHelper;
    private final RealmHelper realmHelper;

    @Inject
    public DataManager(InnovatubeService service, PreferenceHelper preferenceHelper, RealmHelper realmHelper) {
        this.service = service;
        this.preferenceHelper = preferenceHelper;
        this.realmHelper = realmHelper;
    }

    public Observable<Story> getTopStoryId() {
        return service.getTopStoryId()
                .concatMap(new Func1<List<Long>, Observable<? extends Story>>() {
                    @Override
                    public Observable<? extends Story> call(List<Long> longs) {
                        return getPostsFromIds(longs);
                    }
                });
    }

    public Observable<Story> getNewStoryId() {
        return service.getNewStoryId()
                .concatMap(new Func1<List<Long>, Observable<? extends Story>>() {
                    @Override
                    public Observable<? extends Story> call(List<Long> longs) {
                        return getPostsFromIds(longs);
                    }
                });
    }

    public Observable<Story> getPostsFromIds(List<Long> storyIds) {
        return Observable.from(storyIds)
                .concatMap(new Func1<Long, Observable<Story>>() {
                    @Override
                    public Observable<Story> call(Long aLong) {
                        return service.getStoryItem(String.valueOf(aLong));
                    }
                }).flatMap(new Func1<Story, Observable<Story>>() {
                    @Override
                    public Observable<Story> call(Story story) {
                        return story.getTitle() != null ? Observable.just(story) : Observable.<Story>empty();
                    }
                });
    }

    public void saveUrl(String url) {
        preferenceHelper.setUrl(url);
    }

    public String getUrl() {
        return preferenceHelper.getUrl();
    }

    public void saveStory(Story story, Realm realm) {
        realmHelper.saveStory(story, realm);
    }
}
