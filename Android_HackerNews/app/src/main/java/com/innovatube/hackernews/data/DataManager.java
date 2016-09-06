package com.innovatube.hackernews.data;

import com.innovatube.hackernews.data.model.Story;
import com.innovatube.hackernews.data.model.StoryId;
import com.innovatube.hackernews.data.remote.InnovatubeService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Thanh on 05/09/2016.
 */
@Singleton
public class DataManager {
    private final InnovatubeService service;

    @Inject
    public DataManager(InnovatubeService service) {
        this.service = service;
    }

    public Observable<Story> getStoryId() {
        return service.getTopStoryId()
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
}
